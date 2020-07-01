/**
 * 设计模式 - 命令模式
 * 使用命令对象
 */
public class SimpleRemoteControl {
    Command slot;   // 有一个插槽持有命令，而这个命令控制着一个装置

    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}