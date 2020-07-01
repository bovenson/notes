/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * 饮料类 - HouseBlend
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    public double cost() {
        return .89;
    }
}