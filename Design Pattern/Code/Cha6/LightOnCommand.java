/**
 * 设计模式 - 命令模式
 * 打开灯命令
 */
public class LightOnCommand implements Command {    // 这是一个命令，需要实现Command接口
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
}