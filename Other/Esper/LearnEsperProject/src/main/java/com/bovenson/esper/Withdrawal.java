package com.bovenson.esper;

public class Withdrawal {
    private String name;
    private int amount;

    public Withdrawal(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
