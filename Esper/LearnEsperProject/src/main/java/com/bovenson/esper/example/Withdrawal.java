package com.bovenson.esper.example;

import java.util.ArrayList;
import java.util.List;

public class Withdrawal {
    private String name;
    private int amount;
    private PersonEvent person;

    public PersonEvent getPerson() {
        return person;
    }

    public void setPerson(PersonEvent person) {
        this.person = person;
    }

    private List<PersonEvent> persons = new ArrayList<PersonEvent>();

    public List<PersonEvent> getPersons() {
        return persons;
    }

    public void addPerson(PersonEvent p) {
        this.persons.add(p);
    }

    public void setPersons(List<PersonEvent> persons) {
        this.persons = persons;
    }

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
