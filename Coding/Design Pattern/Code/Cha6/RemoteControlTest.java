/**
 * 设计模式 - 命令模式
 * 测试程序
 */
public class RemoteControlTest {    // 这是命令模式的客户
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl(); // 遥控器就是调用者，传入一个命令对象，可以用来发出请求
        Light light = new Light();  // 创建一个点灯对象，此对象也就是请求的接受者

        LightOnCommand lightOn = new LightOnCommand(light); // 这里创建一个命令，然后将接受者传给它

        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}