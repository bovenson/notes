/**
 * Head First 设计模式 - 装饰者模式
 * 星巴兹
 * CondimentDecorator   调料类
 */
public abstract class CondimentDecorator extends Beverage { // 首先，必须让Condiment Decorator能取代Beverage(使用继承)
    public abstract String getDescription();    // 所有的调料装饰者都必须重新实现getDescription()方法
}