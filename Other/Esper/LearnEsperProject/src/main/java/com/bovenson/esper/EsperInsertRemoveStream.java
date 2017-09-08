package com.bovenson.esper;

import com.espertech.esper.client.*;

public class EsperInsertRemoveStream {
    public static void main(String args[]) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(Withdrawal.class);

        String epl = String.format("select * from %s#length(2)", Withdrawal.class.getName());
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

        // 添加 update listener
        statement.addListener(new WithdrawalUpdateListener());
//        statement.addListener((newData, oldData) -> {
//            if (newData != null) {
//                String name = (String) newData[0].get("name");
//                int amount = (int) newData[0].get("amount");
//                System.out.println(String.format("newData info - Name: %s, Amount: %d", name, amount));
//            } else {
//                System.out.println("newData is null.");
//            }
//
//            if (oldData != null) {
//                String name = (String) oldData[0].get("name");
//                int amount = (int) oldData[0].get("amount");
//                System.out.println(String.format("oldData info - Name: %s, Amount: %d", name, amount));
//            } else {
//                System.out.println("oldData is null.");
//            }
//        });

        // 发送事件
        EPRuntime runtime = engine.getEPRuntime();
        runtime.sendEvent(new Withdrawal("W1", 500));
        runtime.sendEvent(new Withdrawal("W2", 100));
        runtime.sendEvent(new Withdrawal("W3", 200));
        runtime.sendEvent(new Withdrawal("W4", 300));
        runtime.sendEvent(new Withdrawal("W5", 50));
        runtime.sendEvent(new Withdrawal("W6", 150));
    }


    static class WithdrawalUpdateListener implements UpdateListener {

        @Override
        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
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
        }
    }
}


