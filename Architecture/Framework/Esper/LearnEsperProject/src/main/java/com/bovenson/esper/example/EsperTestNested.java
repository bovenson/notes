package com.bovenson.esper.example;

import com.espertech.esper.client.*;

public class EsperTestNested {

    public static void main(String args[]) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(Withdrawal.class);

        // 属性过滤
        // String epl = String.format("select irstream * from %s(person.age>30)", Withdrawal.class.getName());
        // 嵌套过滤
        // String epl = String.format("select irstream * from %s(persons[0].age>30)", Withdrawal.class.getName());
        // 列表过滤
        String epl = String.format("select irstream * from %s(persons.allOf( v => v.age > 20))", Withdrawal.class.getName());
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

        // 添加 update listener
        statement.addListener((EventBean[] newEvents, EventBean[] oldEvents) -> {
            if (newEvents != null) {
                String name = (String) newEvents[0].get("name");
                int amount = (int) newEvents[0].get("amount");
                PersonEvent person = (PersonEvent) newEvents[0].get("person");
                System.out.println(String.format("newEvents info - Name: %s, Amount: %d, Person Age: %d", name, amount, person.getAge()));
            } else {
                System.out.println("newEvents is null.");
            }

            if (oldEvents != null) {
                String name = (String) oldEvents[0].get("name");
                int amount = (int) oldEvents[0].get("amount");
                PersonEvent person = (PersonEvent) oldEvents[0].get("person");
                System.out.println(String.format("oldEvents info - Name: %s, Amount: %d, Person Age: %d", name, amount, person.getAge()));
            } else {
                System.out.println("oldEvents is null.");
            }
        });

        PersonEvent p1 = new PersonEvent("AA", 10);
        PersonEvent p2 = new PersonEvent("BB", 30);
        PersonEvent p3 = new PersonEvent("CC", 90);
        PersonEvent p4 = new PersonEvent("DD", 70);
        PersonEvent p5 = new PersonEvent("EE", 80);

        Withdrawal w1 = new Withdrawal("W1", 500);
        w1.addPerson(p1);
        w1.addPerson(p2);
        w1.addPerson(p3);
        Withdrawal w2 = new Withdrawal("W1", 100);
        w2.addPerson(p4);
        w2.addPerson(p5);
        Withdrawal w3 = new Withdrawal("W1", 200);
        w3.addPerson(p1);
        w3.addPerson(p5);
        Withdrawal w4 = new Withdrawal("W1", 50);
        w4.addPerson(p2);
        w4.addPerson(p3);
        w4.addPerson(p4);
        w4.addPerson(p5);
        Withdrawal w5 = new Withdrawal("W1", 150);
        w5.addPerson(p3);
        w5.addPerson(p5);
        Withdrawal w6 = new Withdrawal("W1", 300);
        w6.addPerson(p1);
        w6.addPerson(p4);
        w6.addPerson(p5);

        w1.setPerson(p1);
        w2.setPerson(p2);
        w3.setPerson(p3);
        w4.setPerson(p4);
        w5.setPerson(p5);
        w6.setPerson(p3);

        EPRuntime runtime = engine.getEPRuntime();
        runtime.sendEvent(w1);
        runtime.sendEvent(w2);
        runtime.sendEvent(w3);
        runtime.sendEvent(w4);
        runtime.sendEvent(w5);
        runtime.sendEvent(w6);
    }
}
