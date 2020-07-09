# Build In

## `@Retention`

指明注解生命周期。

**取值**

| 取值    | 说明                                                         |
| ------- | ------------------------------------------------------------ |
| CLASS   | 注解被保留到class文件，但jvm加载class文件时候被遗弃，**默认的生命周期** |
| RUNTIME | 注解不仅被保存到class文件中，jvm加载class文件后，仍然存在    |
| SOURCE  | 注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃 |

**示例**

```java
@Retention(RetentionPolicy.RUNTIME)
```

## `@Target`

指明注解修饰对象范围。

**取值**

| 取值            | 说明                                                         | 示例 |
| --------------- | ------------------------------------------------------------ | ---- |
| CONSTRUCTOR     | 用于描述构造器                                               |      |
| FIELD           | 用于描述域                                                   |      |
| LOCAL_VARIABLE  | 用于描述局部变量                                             |      |
| METHOD          | 用于描述方法                                                 |      |
| PACKAGE         | 用于描述包                                                   |      |
| PARAMETER       | 用于描述参数                                                 |      |
| TYPE            | 用于描述类、接口(包括注解类型) 或enum声明                    |      |
| ANNOTATION_TYPE | 用于注解类型                                                 |      |
| TYPE_PARAMETER  | 类型变量的声明语句中                                         |      |
| TYPE_USE        | 使用类型的任何语句中，比如声明语句、泛型和强制转换语句中的类型 |      |

**示例**

```java
@Target(ElementType.ANNOTATION_TYPE)
```

# 自定义注解

**示例**

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface {}
```

