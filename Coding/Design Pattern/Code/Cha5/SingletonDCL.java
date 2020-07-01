/**
 * 单件模式
 * 双重检查加锁(double-checked locking)
 */
public class SingletonDCL {
    // volatile关键词确保：当uniqueInstance变量被初始化成Singleton实例时
    // 多个线程正确地处理uniqueInstance变量
    // volatile不适用1.4及更早版本
    private volatile static SingletonDCL uniqueInstance;   

    private SingletonDCL() {}

    public static SingletonDCL getInstance() {
        if (uniqueInstance == null) {   // 如果实例不存在进入同步区； 只有第一次执行才会彻底执行这里的代码
            synchronized (SingletonDCL.class) { 
                if (uniqueInstance == null) {   // 进入区块后，再检查一次，如果仍是null，才创建实例
                    uniqueInstance = new SingletonDCL();
                }
            }
        }
        return uniqueInstance;
    }
}