# Http Request

```java
// construct
RequestConfig requestConfig = RequestConfig.custom()
  .setConnectTimeout(100)
  .setSocketTimeout(100)
  .setConnectionRequestTimeout(100)
  .build();
HttpClient httpClient = HttpClients.createDefault();

// post
String url = "http://localhost";
HttpPost request = new HttpPost(url);
request.setConfig(requestConfig);

HttpEntity entity = MultipartEntityBuilder
        .create()
        .addTextBody("uid", "uid")
        .addTextBody("data", "data")
        .build();

request.setEntity(entity);

// get
URIBuilder builder = new URIBuilder("http://localhost");
builder.setParameter("uid", "uid")
 	 			.setParameter("data", "data");
HttpGet request = new HttpGet(builder.build());
request.setConfig(requestConfig);
httpClient.execute(request);
```

# Java Socket发送文件

## Client

```java
package com.neu.nsr.cloudsecurity.demoapp.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class SendFileThread extends Thread {
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private BufferedReader bufferedReader = null;
	private Socket socket = null;
	private int length;
	private String line = null;
	private JTextArea textArea;
	private File file;

	private int intervalTime = 5;			// 发送间隔(秒)
	private boolean runFlag = false;
	private String destIp;
	private int destPort;

	public SendFileThread(File file, String destIp, int destPort, JTextArea textArea) {		
		this.textArea = textArea;
		this.file = file;
		this.destIp = destIp;
		this.destPort = destPort;
	}

	@Override
	public void run() {
		while (true && this.isInterrupted()==false) {
			if (this.runFlag) {
				sendFile();
				try {
					Thread.sleep(this.intervalTime * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void sendFile() {
		try {
			this.appendShowInfo("向" + destIp + ":" + destPort + "发送文件:" + file + ", 文件内容:");
			this.appendShowInfo(this.file);

			this.socket = new Socket(destIp, destPort);
			this.socket.setSoTimeout(3000);
			// 与客户端建立通信，获取输入流，读取取客户端提供的信息
			dos = new DataOutputStream(socket.getOutputStream());				// socket数据输出流
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));		// socket数据输入流
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 缓冲区
			byte[] bufArray = new byte[1024];
			dos.writeUTF(file.getName());
			dos.writeLong(file.length());
			// 发送文件
            while((length = dis.read(bufArray, 0, bufArray.length)) > 0){
            	dos.write(bufArray, 0, length);
            	dos.flush();
            }
			dos.flush();

			// 接受文件接收端回执信息
			try {
				while ((line = bufferedReader.readLine()) != null) {
					this.appendShowInfo("文件接受端消息回执: " + line);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("time out");
			}
			this.appendShowInfo("");

		} catch (ConnectException e) {
			e.printStackTrace();
			this.appendShowInfo("连接失败！");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (dos != null)
					dos.close();
				if (dis != null)
					dis.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRunFlag() {
		return runFlag;
	}

	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	private void appendShowInfo(String info) {
		this.textArea.setText(this.textArea.getText() + info + "\r\n");

		this.textArea.selectAll();  
		this.textArea.setCaretPosition(this.textArea.getSelectedText().length());  
		this.textArea.requestFocus();  
	}
	
	private void appendShowInfo(File file) {
		String line;
		BufferedReader bf;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while((line = bf.readLine()) != null){
    			appendShowInfo(line);
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			appendShowInfo("文件不存在");
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}

```

## Server

```java
package com.neu.nsr.cloudsecurity.demoapp.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class ServerThread implements Runnable {
	private Socket socket = null;
	private JTextArea textArea = null;
	public ServerThread(Socket socket, JTextArea textArea) {
		this.socket = socket;
		this.textArea = textArea;
	}

	@Override
	public void run() {
		OutputStreamWriter osw = null;
		DataInputStream dis = null;
		DataOutputStream fileOut = null;
		byte[] buf = new byte[1024];
		long fileLength;
		
		try {
			socket.setSoTimeout(3000);
			// 与客户端建立通信，获取输入流，读取取客户端提供的信息
			osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");	// socket输出流
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));	// 文件输入流
			String savePath = dis.readUTF();
			this.appendShowInfo("开始接受文件: " + savePath);
			fileLength = dis.readLong();
			fileOut = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(savePath))));
			
			while (true && fileLength > 0) {
				int read = dis==null ? -1 : dis.read(buf, 0, buf.length);
				fileLength -= read;
				if (read <= 0) {
					break;
				}
				fileOut.write(buf, 0, read);
				fileOut.flush();
			}
			
			this.appendShowInfo("文件接收完成: " + savePath);
			osw.write("接收完成，文件存为" + savePath + "\r\n");
			osw.flush();
			this.appendShowInfo("打印接受文件内容:");
			this.appendShowInfo(new File(savePath));

			this.appendShowInfo("");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源即相关socket
			try {
				if (osw != null)
					osw.close();
				if (dis != null)
					dis.close();
				if (fileOut != null)
					fileOut.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void appendShowInfo(String info) {
		this.textArea.setText(this.textArea.getText() + info + "\r\n" );

		this.textArea.selectAll();  
		this.textArea.setCaretPosition(this.textArea.getSelectedText().length());  
		this.textArea.requestFocus();  
	}
	
	private void appendShowInfo(File file) {
		String line;
		BufferedReader bf;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while((line = bf.readLine()) != null){
    			appendShowInfo(line);
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			appendShowInfo("文件不存在");
		} catch (Exception e) { 
			e.printStackTrace();

		}
	}
}
```