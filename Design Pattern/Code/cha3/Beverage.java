/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * Beverage
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}