---
title: SpringMVC 配置
tags:
	- SpringMVC
categories:
	- Java
---

# 配置前端控制器

在 web.xml 文件中进行如下配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xmlns="http://java.sun.com/xml/ns/javaee"
     xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
     http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>SpringMVC_01</display-name>
  <!-- 配置前端控制器DispatcherServlet -->
  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--springmvc.xml 是自己创建的SpringMVC全局配置文件，用contextConfigLocation作为参数名来加载
        如果不配置 contextConfigLocation，那么默认加载的是/WEB-INF/servlet名称-servlet.xml，在这里也就是 springmvc-servlet.xml
      -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
    </init-param>
  </servlet>
 
  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <!--第一种配置：*.do,还可以写*.action等等，表示以.do结尾的或者以.action结尾的URL都由前端控制器DispatcherServlet来解析
        第二种配置：/,所有访问的 URL 都由DispatcherServlet来解析，但是这里最好配置静态文件不由DispatcherServlet来解析
        错误配置：/*,注意这里是不能这样配置的，应为如果这样写，最后转发到 jsp 页面的时候，仍然会由DispatcherServlet进行解析，
                    而这时候会找不到对应的Handler，从而报错！！！
      -->
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
</web-app>
```

# 配置处理器适配器

在 springmvc.xml 文件中配置。用来约束我们所需要编码的 Handler类。

**第一种配置：编写 Handler 时必须要实现 Controller**

```xml
<!-- 配置处理器适配器，所有适配器都得实现 HandlerAdapter接口 -->
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter" />
```

源码：

```java
public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    public boolean supports(Object handler) {	
        // 编写Handle必须要实现Controller,否则不能被适配器解析
        return (handler instanceof Controller)
    }
    
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throw Exception {
        return ((Controller) handler).handlerRequest(request, response);
    }
}
```

**第二种配置：编写 Handler 时必须要实现 HttpRequestHandler**

```xml
<!-- 配置处理器适配器第二种方法，所有适配器都得实现 HandlerAdapter接口 ，这样配置所有Handler都得实现 HttpRequestHandler接口-->
<bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter" />
```

源码：

```java
public class HttpRequestHandlerAdapter implments HandlerAdapter {
    public boolean supports(Object handler) {
        // 自定义Handler必须要实现HttpRequestHandler
        return (handler instanceof HttpRequestHandler);
    }
}
```

# 编写Handler

在 springmvc.xml 文件中配置。通俗来讲，就是请求的 URL 到我们这里所编写的 Handler 类的某个方法进行一些业务逻辑处理。

我们在上面讲解了两个处理器适配器来约束 Handler，那么我们就通过上面两种配置分别编写两个 Handler

 **第一种：实现Controller 接口**

```java
package com.ys.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
 
public class HelloController implements Controller{
 
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelView = new ModelAndView();
        //类似于 request.setAttribute()
        modelView.addObject("name","张三");
        modelView.setViewName("/WEB-INF/view/index.jsp");
        return modelView;
    }
}
```

**第二种：实现 HttpRequestHandler 接口**

```java
package com.ys.controller;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.HttpRequestHandler;
 
public class HelloController2 implements HttpRequestHandler{
 
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("name", "张三");
        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }
}
```

通常我们使用第一种方式来编写 Handler ，但是第二种没有返回值，我们可以通过 response 修改相应内容，比如返回 json 数据。

```java
response.setCharacterEncoding("utf-8");
response.setContentType("application/json;charset=utf-8");
response.getWriter().write("json字符串");
```

# 配置处理器映射器

在 springmvc.xml 文件中配置。通俗来讲就是请求的 URL 怎么能被 SpringMVC 识别，从而去执行我们上一步所编写好的 Handler

**第一种方法：**

```xml
<!-- 配置Handler -->   
<bean name="/hello.do" class="com.ys.controller.HelloController2" />
 
<!-- 配置处理器映射器
    将bean的name作为url进行查找，需要在配置Handler时指定bean name（就是url）-->
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping" />
```

这样配置的话，那么请求的 URL，必须为 http://localhost:8080/项目名/hello.do

**第二种方法：**

```xml
<!-- 配置Handler -->   
<bean id="hello1" class="com.ys.controller.HelloController" />
<bean id="hello2" class="com.ys.controller.HelloController" />
<!-- 第二种方法：简单URL配置处理器映射器 -->
<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
    <property name="mappings">
        <props>
            <prop key="/hello1.do">hello1</prop>
            <prop key="/hello2.do">hello2</prop>
        </props>
    </property>
</bean>
```

这种配置请求的 URL可以为 http://localhost:8080/项目名/hello1.do，或者http://localhost:8080/项目名/hello2.do。

上面两种处理器映射器配置可以并存，前端控制器会正确的去判断 url 用哪个 Handler 去处理。

# 配置视图解析器

**第一种配置:**

```xml
<!-- 配置视图解析器
    进行jsp解析，默认使用jstl标签，classpath下得有jstl的包-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" />
```

如果这样配，那么在 Handler 中返回的必须是路径+jsp页面名称+".jsp"　

**第二种配置：**

```xml
<!--配置视图解析器  -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!-- 返回视图页面的前缀 -->
    <property name="prefix" value="/WEB-INF/view"></property>
    <!-- 返回页面的后缀 -->
    <property name="suffix" value=".jsp"></property>
</bean>
```

如果这样配，那么在 Handler 中只需要返回在 view 文件夹下的jsp 页面名就可以了。

# DispatcherServlet.properties

上面我们讲解了各种配置，可能有人会问这么多配置，万一少配置了一样，那不就不能运行了，那我们能不能不配置呢？答案是肯定的，SpringMVC 给我们提供了一个 DispatcherServlet.properties 文件。系统会首先加载这里面的配置，如果我们没有配置，那么就默认使用这个文件的配置；如果我们配置了，那么就优先使用我们手动配置的。

在 SpringMVC 运行之前，会首先加载 DispatcherServlet.properties 文件里面的内容。

```properties
# Default implementation classes for DispatcherServlet's strategy interfaces.
# Used as fallback when no matching beans are found in the DispatcherServlet context.
# Not meant to be customized by application developers.
 
org.springframework.web.servlet.LocaleResolver=org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
 
org.springframework.web.servlet.ThemeResolver=org.springframework.web.servlet.theme.FixedThemeResolver
 
org.springframework.web.servlet.HandlerMapping=org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping,\
    org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping
 
org.springframework.web.servlet.HandlerAdapter=org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter,\
    org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter,\
    org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter
 
org.springframework.web.servlet.HandlerExceptionResolver=org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver,\
    org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver,\
    org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver
 
org.springframework.web.servlet.RequestToViewNameTranslator=org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator
 
org.springframework.web.servlet.ViewResolver=org.springframework.web.servlet.view.InternalResourceViewResolver
 
org.springframework.web.servlet.FlashMapManager=org.springframework.web.servlet.support.SessionFlashMapManager
```

- 处理器适配器默认：org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
- 处理器映射器默认：org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
- 视图解析器默认：org.springframework.web.servlet.view.InternalResourceViewResolver

#  参考

- [博客园](https://www.cnblogs.com/ysocean/p/7375405.html)