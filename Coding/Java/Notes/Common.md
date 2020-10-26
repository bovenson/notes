[TOC]

# Java 编译与反编译

- `jar cvf a.jar *.class`打包程序，然后`jad -d d:\ -r -s java d:\*.class`反编译程序

```java
// 示例代码
public class Concatenation {
	public static void main(String args[]) {
		String mango = "mango";
		String s = "abc" + mango + "def" + 47;
		System.out.println(s);
	}
}
```

```shell
### javap -c 
# -c 标志表示将生成JVM字节码
$ javac Concatenation.java 
$ java Concatenation
abcmangodef47
$ javap -c Concatenation
Compiled from "Concatenation.java"
public class Concatenation {
  public Concatenation();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // String mango
       2: astore_1
       3: new           #3                  // class java/lang/StringBuilder
       6: dup
       7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
      10: ldc           #5                  // String abc
      12: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      15: aload_1
      16: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      19: ldc           #7                  // String def
      21: invokevirtual #6                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      24: bipush        47
      26: invokevirtual #8                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      29: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      32: astore_2
      33: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
      36: aload_2
      37: invokevirtual #11                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      40: return
}
```

- 其中dup和invokevirtual语句相当于JVM上的汇编语句

# 对象池

```code
StackObjectPool recMetaRequestPool = new StackObjectPool(new BasePoolableObjectFactory() {
    @Override
    public Object makeObject() throws Exception {
        return new Something();
    }
});
```

# 序列化反序列化

```java
# define structure
public class TestSerialize implements Serializable {
  ...
}
TestSerialize obj = new TestSerialize();

# by apache commons-lang3
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.10</version>
</dependency>

# searialize
byte[] data = SerializationUtils.serialize(obj);

# desearialize
BloomFilter<String> nob = SerializationUtils.deserialize(data);
```

# Java随机数

```java
Random random = new Random();	// 实例化对象, 默认种子是当前系统时间的毫秒数
random.nextInt(100)				// 返回[0, 100)内随机数
random.nextInt(100) + 100		// 获取[100, 200)内随机数
random.nextInt()				// 返回随机整数
```

# Java事件监听的四种方法

## 自身类实现ActionListener接口，作为事件监听器

```java
class EventListener1 extends JFrame implements ActionListener {
  private JButton btBlue, btDialog;

  public EventListener1() {
    setTitle("Java GUI 事件监听处理");
    setBounds(100, 100, 500, 350);
    setLayout(new FlowLayout());
    btBlue = new JButton("蓝色");   
    btDialog = new JButton("弹窗");
    
    // 将按钮添加事件监听器
    btBlue.addActionListener(this);
    btDialog.addActionListener(this);

    add(btBlue);
    add(btDialog);

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  // ***************************事件处理***************************
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btBlue) {
      Container c = getContentPane();
      c.setBackground(Color.BLUE);
    }
    else if (e.getSource() == btDialog) {
      JDialog dialog = new JDialog();
      dialog.setBounds(300, 200, 400, 300);
      dialog.setVisible(true);
    }
  }
}
```

## 内部类处理

```java
class EventListener3 extends JFrame {
  private JButton btBlue, btDialog;

  // 构造方法
  public EventListener3() {
    setTitle("Java GUI 事件监听处理");
    setBounds(100, 100, 500, 350);
    setLayout(new FlowLayout());
    btBlue = new JButton("蓝色");
    btDialog = new JButton("弹窗");
    // 添加事件监听器对象(面向对象思想)
    btBlue.addActionListener(new ColorEventListener());
    btDialog.addActionListener(new DialogEventListener());

    add(btBlue);
    add(btDialog);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  // 内部类ColorEventListener，实现ActionListener接口
  class ColorEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Container c = getContentPane();
      c.setBackground(Color.BLUE);
    }
  }
  // 内部类DialogEventListener，实现ActionListener接口
  class DialogEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JDialog dialog = new JDialog();
      dialog.setBounds(300, 200, 400, 300);
      dialog.setVisible(true);
    }
  }

}
```

## 匿名内部类处理

```java
class EventListener2 extends JFrame {
  private JButton btBlue, btDialog;

  public EventListener2() {
    setTitle("Java GUI 事件监听处理");
    setBounds(100, 100, 500, 350);
    setLayout(new FlowLayout());

    btBlue = new JButton("蓝色");
    btDialog = new JButton("弹窗");
    
    // 添加事件监听器(此处即为匿名类)
    btBlue.addActionListener(new ActionListener() {
      // 事件处理
      @Override
      public void actionPerformed(ActionEvent e) {
        Container c = getContentPane();
        c.setBackground(Color.BLUE);
      }
    });
    
    // 并添加事件监听器 
    btDialog.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setBounds(300, 200, 400, 300);
        dialog.setVisible(true);
      }
    });

    add(btBlue);
    add(btDialog);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
```

## 外部类处理

```java
class EventListener4 extends JFrame {
  private JButton btBlue, btDialog;

  public EventListener4() {
    setTitle("Java GUI 事件监听处理");
    setBounds(100, 100, 500, 350);
    setLayout(new FlowLayout());
    btBlue = new JButton("蓝色");
    btDialog = new JButton("弹窗");
    // 将按钮添加事件监听器
    btBlue.addActionListener(new ColorEventListener(this));
    btDialog.addActionListener(new DialogEventListener());

    add(btBlue);
    add(btDialog);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
// 外部类ColorEventListener，实现ActionListener接口
class ColorEventListener implements ActionListener {
  private EventListener4 el;
  ColorEventListener(EventListener4 el) {
    this.el = el;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    Container c = el.getContentPane();
    c.setBackground(Color.BLUE);
  }
}
// 外部类DialogEventListener，实现ActionListener接口
class DialogEventListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    JDialog dialog = new JDialog();
    dialog.setBounds(300, 200, 400, 300);
    dialog.setVisible(true);
  }
}

public class ActionListenerTest
{
  public static void main(String args[])
  {
    new EventListener2();
  }
}
```

**参考**

[参考一](http://www.jb51.net/article/55452.htm)

# File
```java
File file = new File("/home/t.c");
String fileName = file.getName();			// 文件名
String fileParent = file.getParent();		// 文件所在目录(除去文件名的路径)
String filePath = file.getAbsolutePath();	// 包含文件名及路径的路径
```

## 读取文件

```java
// buffered reader
File file = new File("name.ext");
BufferedReader br = new BufferedReader(new FileReader(file));
String st;
while((st = br.readLine()) != null){
  // do something
}

// scanner
File file = new File("name.ext");
Scanner sc = new Scanner(file);
while(sc.hasNextLine()){
  // do something
}

// read from resource
ClassLoader classloader = Thread.currentThread().getContextClassLoader();
InputStream is = classloader.getResourceAsStream("name.ext");
InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
BufferedReader br = new BufferedReader(streamReader);
String st;
while((st = br.readLine()) != null){
  // do something
}
```

