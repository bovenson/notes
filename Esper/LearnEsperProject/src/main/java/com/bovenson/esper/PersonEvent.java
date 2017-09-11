package com.bovenson.esper;

public class PersonEvent {
    private String name;
    private int age;

    public PersonEvent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
