package com.bovenson.esper;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class EsperTest {

    public static void main(String[] args) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(PersonEvent.class);

        String epl = "select name,age from PersonEvent";
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

        statement.addListener((newData, oldData) -> {
            String name = (String)newData[0].get("name");
            int age = (int)newData[0].get("age");
            System.out.println(String.format("Name:%s, Age:%d", name, age));
        });

        engine.getEPRuntime().sendEvent(new PersonEvent("Peter", 10));
    }
}
