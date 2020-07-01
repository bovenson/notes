/**
 * 单件模式
 * 急切（eagerly） 创建
 */
public class SingletonEagerly {
    private static SingletonEagerly uniqueInstance = new SingletonEagerly();

    private SingletonEagerly() {}

    public static SingletonEagerly getInstance() {
        return uniqueInstance;
    }
}