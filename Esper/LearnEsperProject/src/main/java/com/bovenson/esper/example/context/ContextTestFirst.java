package com.bovenson.esper.example.context;


import com.bovenson.esper.example.event.Apple;
import com.espertech.esper.client.*;

public class ContextTestFirst {

    public static void main(String args[]) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = engine.getEPAdministrator();
        EPRuntime runtime = engine.getEPRuntime();

        String esb = Apple.class.getName();

        // 创建Context
        String epl1 = "create context AppleContext partition by id,price from " + esb;
        // String epl1 = "create context AppleContext partition by id,price from " + esb;
        // context.id针对不同的ID,price建立一个context, 如果事件的id和price系统, 则context.id也相同, 即表明事件进入同一个context
        String epl2 = "context AppleContext select context.id,context.name,context.key1,context.key2 from " + esb;

        admin.createEPL(epl1);
        EPStatement state = admin.createEPL(epl2);
        state.addListener((newEvents, oldEvents) -> {
            if (newEvents != null) {
                EventBean event = newEvents[0];
                System.out.println("newEvents length: " + newEvents.length);
                System.out.println("context.name: " + event.get("name") + ",\tcontext.id: " + event.get("id")
                        + ",\tcontext.key1: " + event.get("key1") + ",\tcontext.key2: " + event.get("key2"));
            }
        });

        System.out.println(String.format("Send Event: id=%d, price=%d", 1, 20));
        runtime.sendEvent(new Apple(1, 20));

        System.out.println(String.format("Send Event: id=%d, price=%d", 2, 30));
        runtime.sendEvent(new Apple(2, 30));

        System.out.println(String.format("Send Event: id=%d, price=%d", 1, 20));
        runtime.sendEvent(new Apple(1, 20));

        System.out.println(String.format("Send Event: id=%d, price=%d", 4, 20));
        runtime.sendEvent(new Apple(4, 20));
        state.destroy();

        System.out.println("******************************************************************************");

        String epl4 = "create context AppleContextId partition by id from " + esb;
        admin.createEPL(epl4);
        String epl3 = String.format("context AppleContextId select sum(price) as sum from %s.win:length_batch(2)", esb);
        EPStatement state2 = admin.createEPL(epl3);
        state2.addListener((newEvents, oldEvents) -> {
            if (newEvents != null) {
                System.out.println(newEvents[0].get("sum"));
            }
        });

        runtime.sendEvent(new Apple(1, 20));
        runtime.sendEvent(new Apple(1, 20));
        runtime.sendEvent(new Apple(1, 40));
        runtime.sendEvent(new Apple(1, 50));
    }
}
