public abstract class Duck {

    FlyBehavior flyBehavior;    // 为行为接口类型声明两个引用变量，所有鸭子子类都继承他们
    QuackBehavior quackBehavior;

    public Duck() { }

    public abstract void display();

    public void performFly() {
        flyBehavior.fly();  // 委托给行为类
    }

    public void performQuack() {
        quackBehavior.quack();  // 委托给行为类
    }

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }

    public void setFlyBehavior(FlyBehavior fb) {    // 动态设定行为
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {    // 动态设定行为
        quackBehavior = qb;
    }
}
