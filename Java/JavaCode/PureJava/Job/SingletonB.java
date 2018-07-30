public class SingletonB {
    private SingletonB () {}
    private static SingletonB instance = null;
    public static SingletonB getInstance() {
        synchronized(instance) {
            if (instance == null) {
                instance = new SingletonB();
            }
        }
        return instance;
    }
}
