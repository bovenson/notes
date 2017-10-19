/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * 饮料类 - DarkRoast
 */
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "DarkRoast Coffee";
    }

    public double cost() {
        return .99;
    }
}