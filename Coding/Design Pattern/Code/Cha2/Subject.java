/**
 * 观察者模式案例
 * 气象站主题接口
 */

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();  
}