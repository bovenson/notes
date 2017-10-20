/**
 * 观察者模式案例
 * 气象站观察者接口
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}