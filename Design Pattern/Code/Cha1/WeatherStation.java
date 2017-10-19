/**
 * 观察者模式案例 - 气象站
 * 启动气象站
 */
public class WeatherStation {
    public static void main(String args[]) {
        WeatherData weatherData = new WeatherData();    // 气象站：主题

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);    // 布告板：观察者
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);   // 布告板：观察者
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData); // 布告板：观察者

        weatherData.setMeasurements(80, 65, 30.4f); // 主题更新数据
        weatherData.setMeasurements(82, 70, 29.2f); // 主题更新数据
        weatherData.setMeasurements(78, 90, 29.2f); // 主题更新数据
    }
}