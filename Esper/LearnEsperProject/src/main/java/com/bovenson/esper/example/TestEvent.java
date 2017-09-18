package com.bovenson.esper.example;

public class TestEvent {
    private String name;
    private double price;

    public TestEvent(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
