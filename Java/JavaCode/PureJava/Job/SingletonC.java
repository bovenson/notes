public class SingletonC {
    private SingletonC () {}
    private static SingletonC instance = null;
    public static SingletonC getInstance() {
        if (instance == null) {
            synchronized(instance) {
                if (instance == null) {
                    instance = new SingletonC();
                }
            }
        }
        return instance;
    }
}
