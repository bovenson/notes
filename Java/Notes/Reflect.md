```java
public static void test2(){  
  try {  
    Class<?> c = Class.forName("java.util.Date");  
    //反射类的方法：  
    Method m = c.getMethod("getTime", new Class<?>[]{});  
    long l =(Long)m.invoke(c.newInstance(), new Object[]{});  
    System.out.println(l);  
    //反射一个类的实例：  
    Date d = (Date)c.newInstance();  
    System.out.println(d.getTime());  
  } catch (Exception e) {}  
}  
```

- 反射内部类
  - [click](https://stackoverflow.com/questions/17485297/how-to-instantiate-an-inner-class-with-reflection-in-java)