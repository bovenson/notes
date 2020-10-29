---
title: Java Socket 传输文件
categories: 
	- [coding, java]
tags:
	- java
	- socket
	- 传输文件
date: 2020/10/29 20:00:00
---

# 描述

基于Java Socket传输文件/文件夹，包括客户端、服务端，具备以下特点。

- 一次连接，传输多文件，在传输大量小文件场景下，可节约大量连接创建时间及资源消耗
- 支持传输文件夹，但会跳过空文件夹
- 服务端支持多线程
- 支持结果回显

# 实现

## 依赖

程序集成在Maven工程内，lombok非必需，仅用于提升代码整洁度，如剔除需少量改动代码。

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.16</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.6</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
</dependency>
```

## 基础信息

用于传送每次请求附带参数。

```java
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class FileTransferInfo {
    public static final String TYPE_DELETE = "delete";
    public static final String TYPE_WRITE = "write";
    public static final String TYPE_CLOSE = "close";
    public static final String MSG_DONE = "done";
    public static final String MSG_ERROR = "error";

    private String name;
    private String path;
    private String type;
}
```

## 服务端

服务端包含`FileTransferServer.java` 和 `FileTransferServerThread.java` 两个文件，用于实现多线程支持。

### FileTransferServer

```java
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class FileTransferServer {
    private ServerSocket ss;
    private final int port;
    private final String wd;

    /**
     * @param port network port for listening
     * @param wd   working directory, files will be saved into this folder
     */
    public FileTransferServer(int port, String wd) {
        this.port = port;
        this.wd = wd;
    }

    public void start() throws Exception {
        FileUtils.forceMkdir(new File(wd));

        InetSocketAddress listenAddr = new InetSocketAddress(port);
        ServerSocketChannel listener = ServerSocketChannel.open();
        ss = listener.socket();
        ss.setReuseAddress(true);
        ss.bind(listenAddr);

        while (true) {
            Socket socket = ss.accept();
            System.out.println("Received request: " + socket);
            FileTransferServerThread thread = new FileTransferServerThread(socket, wd);
            thread.start();
        }
    }

    public void close() {
        if (ss != null && !ss.isClosed()) {
            try {
                ss.close();
            } catch (Exception ignored) {
            }
        }
    }
}
```

### FileTransferServerThread

```java
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTransferServerThread extends Thread {
    private static final Gson GSON = new Gson();
    Socket socket;
    String wd;

    public FileTransferServerThread(Socket socket, String wd) {
        this.socket = socket;
        this.wd = wd;
    }

    @Override
    public void run() {
        try {
            boolean loopFlag = true;
            FileTransferInfo info;
            DataOutputStream dot = new DataOutputStream(socket.getOutputStream());
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(bis);

            while (loopFlag) {
                String msg = dis.readUTF();
                info = GSON.fromJson(msg, FileTransferInfo.class);

                if (FileTransferInfo.TYPE_CLOSE.equals(info.type())) {
                    loopFlag = false;
                } else if (FileTransferInfo.TYPE_DELETE.equals(info.type())) {
                    Path fnl = Paths.get(wd, info.path());
                    FileUtils.deleteQuietly(fnl.toFile());
                } else {
                    long length = dis.readLong();

                    Path fnl = Paths.get(wd, info.path(), info.name());
                    System.out.println("Receiving file: " + fnl.toAbsolutePath() + ", length: " + length);
                    FileUtils.forceMkdir(Paths.get(wd, info.path()).toFile());
                    FileUtils.touch(fnl.toFile());

                    FileOutputStream fos = new FileOutputStream(fnl.toFile());
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    for (long j = 0; j < length; j++) {
                        bos.write(bis.read());
                    }
                    bos.close();
                    System.out.println("Saved file: " + fnl.toAbsolutePath());
                }

                dot.writeUTF(FileTransferInfo.MSG_DONE);
                dot.flush();
            }

            System.out.println("Closing socket...");
            dis.close();
            dot.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## 客户端

```java
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class FileTransferClient {
  	private static final Gson GSON = new Gson();
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

    public FileTransferClient(Socket socket) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        this.socket = socket;
        this.dos = new DataOutputStream(bos);
        this.dis = new DataInputStream(socket.getInputStream());
    }

    public void send(String path, File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < files.length; ++i) {
                File f = files[i];
                send(Paths.get(path, file.getName()).toString(), f);
            }
        } else {
            System.out.println("Sending file: " + file.getAbsolutePath() + ", length: " + file.length());
            dos.writeUTF(GSON.toJson(new FileTransferInfo().name(file.getName()).path(path)));
            dos.writeLong(file.length());
            Files.copy(file.toPath(), dos);
            dos.flush();
            System.out.println(dis.readUTF());
        }
    }

    public void close() throws IOException {
        FileTransferInfo info = new FileTransferInfo().type(FileTransferInfo.TYPE_CLOSE);
        dos.writeUTF(GSON.toJson(info));
        dos.flush();
        System.out.println(dis.readUTF());
        dis.close();
        dos.close();
        socket.close();
    }

    public void delete(String path) throws IOException {
        FileTransferInfo info = new FileTransferInfo().path(path).type(FileTransferInfo.TYPE_DELETE);
        dos.writeUTF(GSON.toJson(info));
        dos.flush();
        System.out.println(dis.readUTF());
    }
}
```

# 使用

```java
// 启动服务端
FileTransferServer server = new FileTransferServer(2221, "/tmp");
server.start();

// 客户端传送文件
String host = "127.0.0.1";
int port = 2221;
String path = "receiver";
Socket socket = new Socket(host, port);
FileTransferClient client = new FileTransferClient(socket);
client.delete(path);
client.send(path, new File("/Users/wii/Downloads/cat.jpg"));
client.close();
```

# 更多

- 优化
  - 零拷贝
- 代码
  - [这里](https://github.com/bovenson/demos/tree/master/java/cook-java/cook-sample/src/main/java/pub/wii/cook/java/socket)

