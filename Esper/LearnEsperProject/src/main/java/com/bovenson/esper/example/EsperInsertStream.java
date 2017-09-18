package com.bovenson.esper.example;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class EsperInsertStream {

    public static void main(String args[]) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(EsperSimpleEventBean.class);

        String epl = "select * from " + EsperSimpleEventBean.class.getName();
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

        // 添加 update listener
        statement.addListener((newData, oldData) -> {
            if (newData != null) {
                String name = (String) newData[0].get("name");
                String data = (String) newData[0].get("data");
                System.out.println(String.format("newData info - Name: %s, Data: %s", name, data));
            } else {
                System.out.println("newData is null.");
            }

            if (oldData != null) {
                String name = (String) oldData[0].get("name");
                String data = (String) oldData[0].get("data");
                System.out.println(String.format("oldData info - Name: %s, Data: %s", name, data));
            } else {
                System.out.println("oldData is null.");
            }
        });

        // 发送事件
        engine.getEPRuntime().sendEvent(new EsperSimpleEventBean("Mike", "Good Bye."));
    }

    static class EsperSimpleEventBean {
        private String name;
        private String data;

        EsperSimpleEventBean(String name, String data) {
            this.name = name;
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public String getData() {
            return data;
        }
    }
}
