/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * 装饰者 - Whip
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage; 
    }

    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    public double cost() {
        return .35 + beverage.cost();
    }
}