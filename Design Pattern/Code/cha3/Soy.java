/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * 装饰者 - Soy
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage; 
    }

    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    public double cost() {
        return .50 + beverage.cost();
    }
}