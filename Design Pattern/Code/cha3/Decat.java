/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * 饮料类 - Decat
 */
public class Decat extends Beverage {
    public Decat() {
        description = "Decat Coffee";
    }

    public double cost() {
        return 1.11;
    }
}