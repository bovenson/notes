package com.bovenson.esper.example;

import com.espertech.esper.client.*;

public class TestAA {

    public static void main(String args[]) {
        EPServiceProvider epServiceProvider = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epServiceProvider.getEPAdministrator();

        String product = Apple.class.getName();
        String epl = "select avg(price) from " + product + ".win:length_batch(3)";

        EPStatement state = admin.createEPL(epl);
        state.addListener(new AppleListener());

        EPRuntime runtime = epServiceProvider.getEPRuntime();

        Apple apple1 = new Apple();
        apple1.setId(1);
        apple1.setPrice(3);
        runtime.sendEvent(apple1);

        Apple apple2 = new Apple();
        apple2.setId(1);
        apple2.setPrice(3);
        runtime.sendEvent(apple2);

        Apple apple3 = new Apple();
        apple3.setId(1);
        apple3.setPrice(3);
        runtime.sendEvent(apple3);
    }
}

class AppleListener implements UpdateListener {
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if (newEvents != null) {
            Double avg = (Double) newEvents[0].get("avg(price)");
            System.out.println("Apple's average price is " + avg);
        }
    }
}

class Apple {
    private int id;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
