package com.bovenson.esper.example;

import com.bovenson.esper.Withdrawal;
import com.espertech.esper.client.*;

public class EsperWhereExample {
    public static void main(String args[]) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(Withdrawal.class);

        String epl = String.format("select irstream * from %s where amount >= 200", Withdrawal.class.getName());
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

        // 添加 update listener
        statement.addListener((EventBean[] newEvents, EventBean[] oldEvents) -> {
            if (newEvents != null) {
                String name = (String) newEvents[0].get("name");
                int amount = (int) newEvents[0].get("amount");
                System.out.println(String.format("newEvents info - Name: %s, Amount: %d", name, amount));
            } else {
                System.out.println("newEvents is null.");
            }

            if (oldEvents != null) {
                String name = (String) oldEvents[0].get("name");
                int amount = (int) oldEvents[0].get("amount");
                System.out.println(String.format("oldEvents info - Name: %s, Amount: %d", name, amount));
            } else {
                System.out.println("oldEvents is null.");
            }
            System.out.println("**********************************");
        });

        // 发送事件
        EPRuntime runtime = engine.getEPRuntime();
        runtime.sendEvent(new Withdrawal("W1", 500));
        runtime.sendEvent(new Withdrawal("W2", 100));
        runtime.sendEvent(new Withdrawal("W3", 200));
        runtime.sendEvent(new Withdrawal("W4", 300));
        runtime.sendEvent(new Withdrawal("W5", 50));
        runtime.sendEvent(new Withdrawal("W6", 150));
    }}
