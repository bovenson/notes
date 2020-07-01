package com.bovenson.esper.example;

import com.espertech.esper.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EsperInsertRemoveStreamLambda {
    private static final Logger log = LoggerFactory.getLogger(EsperInsertRemoveStreamLambda.class);

    public static void main(String args[]) throws InterruptedException {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(Withdrawal.class);

        // length window
        // String epl = String.format("select irstream * from %s#length(4)", Withdrawal.class.getName());
        // time window
        String epl = String.format("select irstream * from %s.win:time(1 sec)", Withdrawal.class.getName());
        // String epl = "select * from Withdrawal.win:length(2)";
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

        // 添加 update listener
        statement.addListener((EventBean[] newEvents, EventBean[] oldEvents) -> {
            if (newEvents != null) {
                // System.out.println("Length:" + newEvents.length);
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
        });

        // 发送事件
        EPRuntime runtime = engine.getEPRuntime();
        runtime.sendEvent(new Withdrawal("W1", 500));
        Thread.sleep(1000);
        runtime.sendEvent(new Withdrawal("W2", 100));
        Thread.sleep(1000);
        runtime.sendEvent(new Withdrawal("W3", 200));
        Thread.sleep(1000);
        runtime.sendEvent(new Withdrawal("W4", 300));
        Thread.sleep(1000);
        runtime.sendEvent(new Withdrawal("W5", 50));
        Thread.sleep(1000);
        runtime.sendEvent(new Withdrawal("W6", 150));
    }
}


