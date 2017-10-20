/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * 饮料类 - 浓缩咖啡
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;
    }
}