# 01 - Getting Started

Esper引擎用来解决应用程序对事件分析并作出反应的需求。这些应用程序通常需要实时或者接近实时处理事件或者消息。这种需求简称为复杂事件处理（complex event processing ，CEP）和事件序列分析（event series analysis）。这类需求特点：高吞吐量、低延迟及计算复杂。

## 示例

`Maven` pom文件配置：

```xml
<dependency>
  <groupId>com.espertech</groupId>
  <artifactId>esper</artifactId>
  <version>6.1.0</version>
</dependency>
```

代码：

`PersonEvent.java`

```java
package com.bovenson.esper;

public class PersonEvent {
    private String name;
    private int age;

    PersonEvent(String name, int age) {
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
```

`EsperTest.java`

```java
package com.bovenson.esper;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class EsperTest {

    public static void main(String[] args) {
      	// 获取引擎实例
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

      	// 提供配置信息及输入事件
        engine.getEPAdministrator().getConfiguration().addEventType(PersonEvent.class);

      	// 创建EPL语句
        String epl = "select name,age from PersonEvent";
        EPStatement statement = engine.getEPAdministrator().createEPL(epl);

      	// 附加回调
        statement.addListener((newData, oldData) -> {
            String name = (String)newData[0].get("name");
            int age = (int)newData[0].get("age");
            System.out.println(String.format("Name:%s, Age:%d", name, age));
        });

      	// 发送事件
        engine.getEPRuntime().sendEvent(new PersonEvent("Peter", 10));
    }
}
```

