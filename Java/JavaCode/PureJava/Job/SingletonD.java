public class SingletonD {
    private SingletonD () {}
    private static SingletonD instance = new SingletonD();
    public static SingletonD getInstance() {
        return instance;
    }
}
