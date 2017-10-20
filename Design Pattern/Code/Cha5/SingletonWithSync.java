/**
 * 单件模式
 * 使用同步进行多线程处理
 */
public class SingletonWithSync {
    private static SingletonWithSync uniqueInstance;

    private SingletonWithSync() {}

    /**
     * 通过增加synchronized关键字到getInstance()方法中
     * 迫使每个线程在进入这个方法之前，要先等候别的线程离开该方法
     * 也就是说，不会有两个线程可以同时进入这个方法
     */
    public static synchronized SingletonWithSync getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonWithSync();
        }
        return uniqueInstance;
    }
}