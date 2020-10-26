---
title: SpringMVC 笔记
tags:
	- SpringMVC
categories:
	- JavaEE
---

[toc]

# sevlet

## 简介

Servlet是用来处理客户端请求并产生动态网页内容的Java类。Servlet主要是用来处理或者是存储HTML表单提交的数据，产生动态内容，在无状态的HTTP协议下管理状态信息。

- 处理客户端请求（表单提交的信息等）
- 产生动态网页内容 

使用过程：初始化——service（分析客户端请求,比如是get还是post）——销毁，一个Servlet实例只会创建一次，重复调用的只是service过程。



**HttpServlet** 

- 是一个 Servlet, 继承自 GenericServlet. 针对于 HTTP 协议所定制. 
- 在 service() 方法中直接把 ServletReuqest 和 ServletResponse 转为 HttpServletRequest 和 HttpServletResponse。并调用了重载的 service(HttpServletRequest, HttpServletResponse) 在 service(HttpServletRequest, HttpServletResponse) 获取了请求方式: request.getMethod(). 根据请求方式有创建了doXxx() 方法(xxx 为具体的请求方式, 比如 doGet, doPost) 

- 实际开发中, 直接继承 HttpServlet, 并根据请求方式复写 doXxx() 方法即可. 
- 好处: 直接由针对性的覆盖 doXxx() 方法; 直接使用 HttpServletRequest 和 HttpServletResponse, 不再需要强转. 

**GenericServlet** 

- 是一个 Serlvet. 是 Servlet 接口和 ServletConfig 接口的实现类. 但是一个抽象类. 其中的 service 方法为抽象方法 
- 如果新建的 Servlet 程序直接继承 GenericSerlvet 会使开发更简洁. 

- 具体实现: 
  - 在 GenericServlet 中声明了一个 SerlvetConfig 类型的成员变量, 在 init(ServletConfig) 方法中对其进行了初始化 
  - 利用 servletConfig 成员变量的方法实现了 ServletConfig 接口的方法
  - 还定义了一个 init() 方法, 在 init(SerlvetConfig) 方法中对其进行调用, 子类可以直接覆盖 init() 在其中实现对 Servlet 的初始化. 
  - 不建议直接覆盖 init(ServletConfig), 因为如果忘记编写 super.init(config); 而还是用了 SerlvetConfig 接口的方法, 则会出现空指针异常. 
  - 新建的 init(){} 并非 Serlvet 的生命周期方法. 而 init(ServletConfig) 是生命周期相关的方法.

## 体系结构

所有的Servlet都必须要实现的核心的接口是javax.servlet.Servlet。每一个Servlet都必须要直接或者是间接实现这个接口，或者是继承javax.servlet.GenericServlet或者javax.servlet.http.HTTPServlet。最后，Servlet使用多线程可以并行的为多个请求服务。

**参考**

- [Ref 1](https://blog.csdn.net/a236209186/article/details/51262646)

## 生命周期

对每一个客户端的请求，Servlet引擎载入Servlet，调用它的init()方法，完成Servlet的初始化。然后，Servlet对象通过为每一个请求单独调用service()方法来处理所有随后来自客户端的请求，最后，调用Servlet(译者注：这里应该是Servlet而不是server)的destroy()方法把Servlet删除掉。

## Servlet链(Servlet Chaining)

Servlet链是把一个Servlet的输出发送给另一个Servlet的方法。第二个Servlet的输出可以发送给第三个Servlet，依次类推。链条上最后一个Servlet负责把响应发送给客户端。

## 获取客户端信息

ServletRequest类可以找出客户端机器的IP地址或者是主机名。getRemoteAddr()方法获取客户端主机的IP地址，getRemoteHost()可以获取主机名。

## 比较

**GenericServlet和HttpServlet**

GenericServlet类实现了Servlet和ServletConfig接口。实现了除了service()之外的其他方法，在创建Servlet对象时，可以继承GenericServlet类来简化程序的代码，但需要实现service()方法。

HttpServlet类继承了GeneriServlet类，为实际开发中大多数用Servlet处理 HTTP请求的应用灵活的方法。

**doGet()方法和doPost()**

doGet：GET方法会把名值对追加在请求的URL后面。因为URL对字符数目有限制，进而限制了用在客户端请求的参数值的数目。并且请求中的参数值是可见的，因此，敏感信息不能用这种方式传递。 doPOST：POST方法通过把请求参数值放在请求体中来克服GET方法的限制，因此，可以发送的参数的数目是没有限制的。最后，通过POST请求传递的敏感信息对外部客户端是不可见的。

**sendRedirect()和forward()**

sendRedirect()方法会创建一个新的请求，而forward()方法只是把请求转发到一个新的目标上。重定向(redirect)以后，之前请求作用域范围以内的对象就失效了，因为会产生一个新的请求，而转发(forwarding)以后，之前请求作用域范围以内的对象还是能访问的。一般认为sendRedirect()比forward()要慢。

# JSP

## 如何被处理

浏览器首先要请求一个以.jsp扩展名结尾的页面，发起JSP请求，然后，Web服务器读取这个请求，使用JSP编译器把JSP页面转化成一个Servlet类。需要注意的是，只有当第一次请求页面或者是JSP文件发生改变的时候JSP文件才会被编译，然后服务器调用servlet类，处理浏览器的请求。一旦请求执行结束，servlet会把响应发送给客户端。这里看下如何在JSP中获取请求参数。

## 优点

下面列出了使用JSP的优点： JSP页面是被动态编译成Servlet的，因此，开发者可以很容易的更新展现代码。 JSP页面可以被预编译。 JSP页面可以很容易的和静态模板结合，包括：HTML或者XML，也可以很容易的和产生动态内容的代码结合起来。 开发者可以提供让页面设计者以类XML格式来访问的自定义的JSP标签库。 开发者可以在组件层做逻辑上的改变，而不需要编辑单独使用了应用层逻辑的页面。

## 指令

Directive是当JSP页面被编译成Servlet的时候，JSP引擎要处理的指令。Directive用来设置页面级别的指令，从外部文件插入数据，指定自定义的标签库。Directive是定义在 <%@ 和 %>之间的。下面列出了不同类型的Directive： 包含指令(Include directive)：用来包含文件和合并文件内容到当前的页面。 页面指令(Page directive)：用来定义JSP页面中特定的属性，比如错误页面和缓冲区。 Taglib指令： 用来声明页面中使用的自定义的标签库。

## JSP动作(JSP action)

JSP动作以XML语法的结构来控制Servlet引擎的行为。当JSP页面被请求的时候，JSP动作会被执行。它们可以被动态的插入到文件中，重用JavaBean组件，转发用户到其他的页面，或者是给Java插件产生HTML代码。

下面列出了可用的动作： jsp:include-当JSP页面被请求的时候包含一个文件。 jsp:useBean-找出或者是初始化Javabean。 jsp:setProperty-设置JavaBean的属性。 jsp:getProperty-获取JavaBean的属性。 jsp:forward-把请求转发到新的页面。 jsp:plugin-产生特定浏览器的代码。

## 隐含对象

JSP隐含对象是页面中的一些Java对象，JSP容器让这些Java对象可以为开发者所使用。开发者不用明确的声明就可以直接使用他们。JSP隐含对象也叫做预定义变量。下面列出了JSP页面中的隐含对象： 

- application 
- page 
- request 
- response 
- session 
- exception 
- out 
- config 
- pageContext