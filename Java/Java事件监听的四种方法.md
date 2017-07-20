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

# 参考

[参考一](http://www.jb51.net/article/55452.htm)