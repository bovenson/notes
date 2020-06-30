---
title: 事务回滚
tags: 
	- 事务回滚 
	- SpringMVC 
categories:
	- JavaEE
---

# 配置

**相关**

```xml
	<!-- 事务管理器 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!-- 数据源 -->
		<property name="dataSource" ref="dataSource" />
	</bean>
	<!-- 通知 -->
	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<!-- 传播行为 -->
			<tx:method name="save*" propagation="REQUIRED" />
			<tx:method name="insert*" propagation="REQUIRED" />
			<tx:method name="add*" propagation="REQUIRED" />
			<tx:method name="create*" propagation="REQUIRED" />
			<tx:method name="delete*" propagation="REQUIRED" />
			<tx:method name="update*" propagation="REQUIRED" />
			<tx:method name="find*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="select*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="get*" propagation="SUPPORTS" read-only="true" />
		</tx:attributes>
	</tx:advice>

	<!-- transaction support-->
	<!-- enable transaction annotation support -->
	<tx:annotation-driven transaction-manager="transactionManager" />
```

**全部**

`springmvc.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
	http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<!-- 数据库连接池 -->
	<!-- 加载配置文件 -->
	<context:property-placeholder location="classpath:resource/*.properties" />
	<!-- 数据库连接池 -->
	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
		destroy-method="close">
		<property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
		<property name="driverClassName" value="${jdbc.driver}" />
		<property name="maxActive" value="10" />
		<property name="minIdle" value="5" />
	</bean>
	<!-- 配置sqlsessionFactory -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="configLocation" value="classpath:mybatis/SqlMapConfig.xml"></property>
		<property name="dataSource" ref="dataSource"></property>
	</bean>
	<!-- 配置扫描包，加载mapper代理对象 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="com.neu.cse.powercloud.mapper"></property>
	</bean>
	<!-- 扫描包加载Service实现类 -->
	<context:component-scan base-package="com.neu.cse.powercloud.serviceImpl"></context:component-scan>
	<!-- 事务管理器 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!-- 数据源 -->
		<property name="dataSource" ref="dataSource" />
	</bean>
	<!-- 通知 -->
	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<!-- 传播行为 -->
			<tx:method name="save*" propagation="REQUIRED" />
			<tx:method name="insert*" propagation="REQUIRED" />
			<tx:method name="add*" propagation="REQUIRED" />
			<tx:method name="create*" propagation="REQUIRED" />
			<tx:method name="delete*" propagation="REQUIRED" />
			<tx:method name="update*" propagation="REQUIRED" />
			<tx:method name="find*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="select*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="get*" propagation="SUPPORTS" read-only="true" />
		</tx:attributes>
	</tx:advice>

	<!-- transaction support-->
	<!-- enable transaction annotation support -->
	<tx:annotation-driven transaction-manager="transactionManager" />

	<!-- 切面 -->
	<aop:config>
		<aop:advisor advice-ref="txAdvice"
			pointcut="execution(* com.neu.cse.powercloud.service.*.*(..))" />
	</aop:config>
</beans>
```

# 示例代码

## 相关

```java
    /**
     * 添加系统用户
     * @param user                SysUser实例
     * @param roles               角色列表
     * @param dataAuthorities   用户数据权限列表
     * @return ResponseResult
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult addUser(SysUser user, List<Integer> roles, List<Integer> dataAuthorities) throws Exception {
        try {
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(user.getUsername());
            if (sysUserMapper.selectByExample(example).isEmpty()) {     // 判断用户名是否已存在
                try {
                    user.setPassword(MD5Encoder.encoderStringByMD5(user.getPassword()));    // 对密码加密
                    sysUserMapper.insert(user);     // 向数据库插入新用户信息
                    // 设置角色
                    // TODO
                    // 设置数据权限
                    // TODO
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e);
                    return ResponseResult.no("用户信息不正确");
                }
                // TODO 删除事务回滚测试抛出异常
                throw new Exception("事务回滚测试");
                // return ResponseResult.ok();
            } else {
                return ResponseResult.no("用户已存在");
            }
        } catch (Exception e) {
            logger.error(e);
            if (e.toString().contains("事务")) {
                throw new Exception("事务回滚测试");
            }
            return ResponseResult.no("未知异常！");
        }
    }

```

## 全部

```java
package com.neu.cse.powercloud.serviceImpl.sysmanage;

import com.github.pagehelper.PageHelper;
import com.neu.cse.powercloud.mapper.sysmanage.SysUserMapper;
import com.neu.cse.powercloud.pojo.sysmanage.SysUser;
import com.neu.cse.powercloud.pojo.sysmanage.SysUserExample;
import com.neu.cse.powercloud.service.sysmanage.UserManageService;
import com.neu.cse.powercloud.serviceImpl.PropertiesServiceImpl;
import com.neu.cse.powercloud.util.MD5Encoder;
import com.neu.cse.powercloud.util.ResponseResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理服务实现
 * @author szk
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private Logger logger = Logger.getLogger(UserManageServiceImpl.class);

    @Autowired(required = true)
    private SysUserMapper sysUserMapper;

    @Autowired
    private PropertiesServiceImpl propertiesServiceImpl;

    /**
     * 添加系统用户
     * @param name                用户名
     * @param password           原始(未经md5加密)密码
     * @param customerId         客户id
     * @param tel                 电话
     * @param status              状态
     * @param roles               角色列表
     * @param dataAuthorities   用户数据权限列表
     * @return  ResponseResult
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult addUser(String name, String password, int customerId, String tel, String status,
                                  List<Integer> roles, List<Integer> dataAuthorities) throws Exception {
        SysUser user = new SysUser();
        user.setPassword(password);
        user.setCustomerid(customerId);
        user.setStatus(status);
        user.setTel(tel);
        user.setUsername(name);
        return this.addUser(user, roles, dataAuthorities);
    }

    /**
     * 添加系统用户
     * @param user                SysUser实例
     * @param roles               角色列表
     * @param dataAuthorities   用户数据权限列表
     * @return ResponseResult
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult addUser(SysUser user, List<Integer> roles, List<Integer> dataAuthorities) throws Exception {
        try {
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(user.getUsername());
            if (sysUserMapper.selectByExample(example).isEmpty()) {     // 判断用户名是否已存在
                try {
                    user.setPassword(MD5Encoder.encoderStringByMD5(user.getPassword()));    // 对密码加密
                    sysUserMapper.insert(user);     // 向数据库插入新用户信息
                    // 设置角色
                    // TODO
                    // 设置数据权限
                    // TODO
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e);
                    return ResponseResult.no("用户信息不正确");
                }
                // TODO 删除事务回滚测试抛出异常
                throw new Exception("事务回滚测试");
                // return ResponseResult.ok();
            } else {
                return ResponseResult.no("用户已存在");
            }
        } catch (Exception e) {
            logger.error(e);
            if (e.toString().contains("事务")) {
                throw new Exception("事务回滚测试");
            }
            return ResponseResult.no("未知异常！");
        }
    }

    /**
     * 获取用户列表
     * 获取区间： [start, min(start+length, total))
     * @param start     开始索引，从0开始
     * @param length    获取长度
     * @return           ResponseResult
     */
    @Override
    public ResponseResult getUsers(int start, int length) {
        try {
            SysUserExample example = new SysUserExample();
            int page = start / length + 1;  // Page 从1开始

            PageHelper.startPage(page, length);     // 设置PageHelper；此处开始分页，获取记录必须放在该语句之后
            List<SysUser> users = sysUserMapper.selectByExample(example);   // 已经被分页

            return ResponseResult.ok(users);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            return ResponseResult.no("未知异常！");
        }
    }

    /**
     * 删除指定id用户
     *
     * @param userId 用户id
     * @return 操作结果
     */
    @Override
    public ResponseResult deleteUser(int userId) {
        try {
            sysUserMapper.deleteByPrimaryKey(userId);
            return ResponseResult.ok();
        } catch (Exception e) {
            logger.error(e);
            return ResponseResult.no("未知异常！");
        }
    }
}

```

# 测试代码

## 相关

```java
    @Test
    public void addUser2() throws Exception {
        // 测试事务回滚
        SysUser user = new SysUser();
        user.setUsername("name" + new Random().nextInt());
        user.setTel("1213123");
        user.setStatus("事务回滚测试");
        user.setCustomerid(10010);
        user.setPassword("passwordPass");

        // 角色
        List<Integer> roles = new ArrayList<Integer>();
        roles.add(1);
        // TODO
        ResponseResult result = userManageService.addUser(user, roles, null);
    }
```

## 全部

```java
package com.neu.cse.powercloud.serviceImpl.sysmanage;

import com.github.pagehelper.Page;
import com.neu.cse.powercloud.pojo.sysmanage.SysUser;
import com.neu.cse.powercloud.service.sysmanage.UserManageService;
import com.neu.cse.powercloud.util.ResponseResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 系统用户管理测试类
 * @author szk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:spring/springmvc.xml", "classpath:mybatis/SqlMapConfig.xml", "classpath:spring/applicationContext.xml"})
public class UserManageServiceImplTest {

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }


    @Test
    public void addUser() throws Exception {
        // 需要登录
        // 发送请求
        String sysUserJson = "{ \"username\": \"admin" + new Random().nextInt() + "\", \"password\": \"admin1\", \"passwordrepeat\": \"admin1\", " +
                "\"customerid\": \"100010\", \"tel\": \"191717171\", \"status\": \"1\"}";
        System.out.println(sysUserJson);

        MvcResult result = this.mockMvc.perform(
                post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sysUserJson)
                        .session((MockHttpSession)getLoginSession()))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.msg").value("OK"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        // System.out.println(userManageService.getUsers(0, 10));
    }

    @Test
    public void addUser1() throws Exception {
        ResponseResult result = userManageService.addUser("name"+new Random().nextInt(), "password", 10010, "18283892", "1", null, null);
        Assert.assertEquals(result.isOk(), true);
    }

    @Test
    public void addUser2() throws Exception {
        // 测试事务回滚
        SysUser user = new SysUser();
        user.setUsername("name" + new Random().nextInt());
        user.setTel("1213123");
        user.setStatus("事务回滚测试");
        user.setCustomerid(10010);
        user.setPassword("passwordPass");

        // 角色
        List<Integer> roles = new ArrayList<Integer>();
        roles.add(1);
        // TODO
        ResponseResult result = userManageService.addUser(user, roles, null);
    }

    @Test
    public void getUsers() throws Exception {
        ResponseResult result = userManageService.getUsers(0, 3);
        Page page = (Page)result.getData();
        System.out.println(page.getTotal());
        System.out.println(page.getResult());
        System.out.println(page.size());    // 该页记录数量

        for (int i=0; i < page.size(); ++i) {
            SysUser tUser = (SysUser) page.get(i);
            System.out.println(tUser);
        }
    }

    @Test
    public void deleteUser() throws Exception {
        ResponseResult result = userManageService.getUsers(0, 3);
        System.out.println(result.getData());
        Assert.assertTrue(result.getData() instanceof Page);
        Page page = (Page)result.getData();
        System.out.println(page);
        long totalBefore = page.getTotal();
        if (page.size() > 0) {
            SysUser deleteUser = (SysUser) page.get(0);
            System.out.println("删除用户:" + deleteUser);
            userManageService.deleteUser(deleteUser.getId());

            result = userManageService.getUsers(0, 3);
            page = (Page)result.getData();
            if (page.size() > 0) {
                Assert.assertNotEquals(((SysUser) page.get(0)).getId(), deleteUser.getId());
            }
            Assert.assertEquals(totalBefore, page.getTotal() + 1);
        }
    }

    /**
     * 获取登录session
     * @return  Session
     * @throws Exception E
     */
    private HttpSession getLoginSession() throws Exception {
        MvcResult result = this.mockMvc.perform(
                post("/login/userLogin")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("account", "admin")
                        .param("password", "admin"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        return result.getRequest().getSession();
    }
}
```

## 效果

- 未插入用户
- 测试日志如下

```shell
/usr/lib/jvm/jdk/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/home/public/installed/idea/lib/idea_rt.jar=41509:/home/public/installed/idea/bin -Dfile.encoding=UTF-8 -classpath /home/public/installed/idea/lib/idea_rt.jar:/home/public/installed/idea/plugins/junit/lib/junit-rt.jar:/usr/lib/jvm/jdk/jre/lib/charsets.jar:/usr/lib/jvm/jdk/jre/lib/deploy.jar:/usr/lib/jvm/jdk/jre/lib/ext/cldrdata.jar:/usr/lib/jvm/jdk/jre/lib/ext/dnsns.jar:/usr/lib/jvm/jdk/jre/lib/ext/jaccess.jar:/usr/lib/jvm/jdk/jre/lib/ext/jfxrt.jar:/usr/lib/jvm/jdk/jre/lib/ext/localedata.jar:/usr/lib/jvm/jdk/jre/lib/ext/nashorn.jar:/usr/lib/jvm/jdk/jre/lib/ext/sunec.jar:/usr/lib/jvm/jdk/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/jdk/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/jdk/jre/lib/ext/zipfs.jar:/usr/lib/jvm/jdk/jre/lib/javaws.jar:/usr/lib/jvm/jdk/jre/lib/jce.jar:/usr/lib/jvm/jdk/jre/lib/jfr.jar:/usr/lib/jvm/jdk/jre/lib/jfxswt.jar:/usr/lib/jvm/jdk/jre/lib/jsse.jar:/usr/lib/jvm/jdk/jre/lib/management-agent.jar:/usr/lib/jvm/jdk/jre/lib/plugin.jar:/usr/lib/jvm/jdk/jre/lib/resources.jar:/usr/lib/jvm/jdk/jre/lib/rt.jar:/home/public/workspace/Idea/PowerCloud/target/test-classes:/home/public/workspace/Idea/PowerCloud/target/classes:/home/public/data/maven/repo/joda-time/joda-time/2.5/joda-time-2.5.jar:/home/public/data/maven/repo/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar:/home/public/data/maven/repo/commons-io/commons-io/1.3.2/commons-io-1.3.2.jar:/home/public/data/maven/repo/commons-net/commons-net/3.3/commons-net-3.3.jar:/home/public/data/maven/repo/com/fasterxml/jackson/core/jackson-databind/2.4.2/jackson-databind-2.4.2.jar:/home/public/data/maven/repo/com/fasterxml/jackson/core/jackson-annotations/2.4.0/jackson-annotations-2.4.0.jar:/home/public/data/maven/repo/com/fasterxml/jackson/core/jackson-core/2.4.2/jackson-core-2.4.2.jar:/home/public/data/maven/repo/org/apache/httpcomponents/httpclient/4.3.5/httpclient-4.3.5.jar:/home/public/data/maven/repo/org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar:/home/public/data/maven/repo/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar:/home/public/data/maven/repo/commons-codec/commons-codec/1.6/commons-codec-1.6.jar:/home/public/data/maven/repo/junit/junit/4.12/junit-4.12.jar:/home/public/data/maven/repo/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/home/public/data/maven/repo/org/mockito/mockito-core/2.10.0/mockito-core-2.10.0.jar:/home/public/data/maven/repo/net/bytebuddy/byte-buddy/1.7.4/byte-buddy-1.7.4.jar:/home/public/data/maven/repo/net/bytebuddy/byte-buddy-agent/1.7.4/byte-buddy-agent-1.7.4.jar:/home/public/data/maven/repo/org/objenesis/objenesis/2.6/objenesis-2.6.jar:/home/public/data/maven/repo/com/jayway/jsonpath/json-path/2.4.0/json-path-2.4.0.jar:/home/public/data/maven/repo/net/minidev/json-smart/2.3/json-smart-2.3.jar:/home/public/data/maven/repo/net/minidev/accessors-smart/1.2/accessors-smart-1.2.jar:/home/public/data/maven/repo/org/ow2/asm/asm/5.0.4/asm-5.0.4.jar:/home/public/data/maven/repo/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/public/data/maven/repo/org/slf4j/slf4j-log4j12/1.6.4/slf4j-log4j12-1.6.4.jar:/home/public/data/maven/repo/log4j/log4j/1.2.16/log4j-1.2.16.jar:/home/public/data/maven/repo/org/mybatis/mybatis/3.2.8/mybatis-3.2.8.jar:/home/public/data/maven/repo/org/mybatis/mybatis-spring/1.2.2/mybatis-spring-1.2.2.jar:/home/public/data/maven/repo/com/github/miemiedev/mybatis-paginator/1.2.15/mybatis-paginator-1.2.15.jar:/home/public/data/maven/repo/com/github/pagehelper/pagehelper/3.4.2/pagehelper-3.4.2.jar:/home/public/data/maven/repo/mysql/mysql-connector-java/5.1.32/mysql-connector-java-5.1.32.jar:/home/public/data/maven/repo/com/alibaba/druid/1.0.9/druid-1.0.9.jar:/usr/lib/jvm/jdk/lib/jconsole.jar:/usr/lib/jvm/jdk/lib/tools.jar:/home/public/data/maven/repo/org/springframework/spring-context/4.1.3.RELEASE/spring-context-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-aop/4.1.3.RELEASE/spring-aop-4.1.3.RELEASE.jar:/home/public/data/maven/repo/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:/home/public/data/maven/repo/org/springframework/spring-core/4.1.3.RELEASE/spring-core-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-expression/4.1.3.RELEASE/spring-expression-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-beans/4.1.3.RELEASE/spring-beans-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-webmvc/4.1.3.RELEASE/spring-webmvc-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-web/4.1.3.RELEASE/spring-web-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-jdbc/4.1.3.RELEASE/spring-jdbc-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-tx/4.1.3.RELEASE/spring-tx-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/springframework/spring-aspects/4.1.3.RELEASE/spring-aspects-4.1.3.RELEASE.jar:/home/public/data/maven/repo/org/aspectj/aspectjweaver/1.8.4/aspectjweaver-1.8.4.jar:/home/public/data/maven/repo/org/springframework/spring-test/4.1.3.RELEASE/spring-test-4.1.3.RELEASE.jar:/home/public/data/maven/repo/jstl/jstl/1.2/jstl-1.2.jar:/home/public/data/maven/repo/javax/servlet/javax.servlet-api/3.0.1/javax.servlet-api-3.0.1.jar:/home/public/data/maven/repo/javax/servlet/jsp-api/2.0/jsp-api-2.0.jar:/home/public/data/maven/repo/javax/servlet/servlet-api/2.4/servlet-api-2.4.jar:/home/public/data/maven/repo/commons-fileupload/commons-fileupload/1.3.1/commons-fileupload-1.3.1.jar com.intellij.rt.execution.junit.JUnitStarter -ideVersion5 com.neu.cse.powercloud.serviceImpl.sysmanage.UserManageServiceImplTest,addUser2
2017-10-16 10:11:47,027 [main] [com.neu.cse.powercloud.serviceImpl.sysmanage.UserManageServiceImpl]-[ERROR] java.lang.Exception: 事务回滚测试

java.lang.Exception: 事务回滚测试

	at com.neu.cse.powercloud.serviceImpl.sysmanage.UserManageServiceImpl.addUser(UserManageServiceImpl.java:93)
	at com.neu.cse.powercloud.serviceImpl.sysmanage.UserManageServiceImplTest.addUser2(UserManageServiceImplTest.java:97)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:73)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:82)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:73)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:217)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:83)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:68)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:163)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:51)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)


Process finished with exit code 255

```

# 问题

## 捕获异常不抛出就不会回滚

**解决方法**

- 例如service层处理事务，那么service中的方法中不做异常捕获，或者在catch语句中最后增加throw new RuntimeException()语句，以便让aop捕获异常再去回滚，并且在service上层（webservice客户端，view层action）要继续捕获这个异常并处理

  ```java
  if(userSave){         
       try {          
          userDao.save(user);          
          userCapabilityQuotaDao.save(capabilityQuota);         
         } catch (Exception e) {         
          logger.info("能力开通接口，开户异常，异常信息："+e);          
          throw new RuntimeException();         
       }          
  }  
  ```

  ​

- 在service层方法的catch语句中增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();语句，手动回滚，这样上层就无需去处理异常

  ```java
  if(userSave){          
      try {          
          userDao.save(user);          
          userCapabilityQuotaDao.save(capabilityQuota);          
      } catch (Exception e) {          
          logger.info("能力开通接口，开户异常，异常信息："+e);          
          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();         
      }         
   } 
  ```

### 修改后代码

```java
package com.neu.cse.powercloud.serviceImpl.sysmanage;

import com.github.pagehelper.PageHelper;
import com.neu.cse.powercloud.mapper.sysmanage.SysUserMapper;
import com.neu.cse.powercloud.pojo.sysmanage.SysUser;
import com.neu.cse.powercloud.pojo.sysmanage.SysUserExample;
import com.neu.cse.powercloud.service.sysmanage.UserManageService;
import com.neu.cse.powercloud.serviceImpl.PropertiesServiceImpl;
import com.neu.cse.powercloud.util.Exception.ServiceRuntimeException;
import com.neu.cse.powercloud.util.MD5Encoder;
import com.neu.cse.powercloud.util.ResponseResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * 用户管理服务实现
 * @author szk
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private Logger logger = Logger.getLogger(UserManageServiceImpl.class);

    @Autowired(required = true)
    private SysUserMapper sysUserMapper;

    @Autowired
    private PropertiesServiceImpl propertiesServiceImpl;

    /**
     * 添加系统用户
     * @param name                用户名
     * @param password           原始(未经md5加密)密码
     * @param customerId         客户id
     * @param tel                 电话
     * @param status              状态
     * @param roles               角色列表
     * @param dataAuthorities   用户数据权限列表
     * @return  ResponseResult
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult addUser(String name, String password, int customerId, String tel, String status,
                                  List<Integer> roles, List<Integer> dataAuthorities) {
        SysUser user = new SysUser();
        user.setPassword(password);
        user.setCustomerid(customerId);
        user.setStatus(status);
        user.setTel(tel);
        user.setUsername(name);
        return this.addUser(user, roles, dataAuthorities);
    }

    /**
     * 添加系统用户
     * @param user                SysUser实例
     * @param roles               角色列表
     * @param dataAuthorities   用户数据权限列表
     * @return ResponseResult
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult addUser(SysUser user, List<Integer> roles, List<Integer> dataAuthorities) {
        try {
            SysUserExample example = new SysUserExample();
            SysUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(user.getUsername());
            if (sysUserMapper.selectByExample(example).isEmpty()) {     // 判断用户名是否已存在
                try {
                    user.setPassword(MD5Encoder.encoderStringByMD5(user.getPassword()));    // 对密码加密
                    sysUserMapper.insert(user);     // 向数据库插入新用户信息
                } catch (Exception e) {
                    throw new ServiceRuntimeException("用户信息错误");
                }

                // 设置角色
                // TODO
                // 设置数据权限
                // TODO

                throw new ServiceRuntimeException("回滚测试");

                // return ResponseResult.ok();
            } else {
                throw new ServiceRuntimeException("用户已存在");
            }
        } catch (ServiceRuntimeException e) {   // 处理自定义异常
            logger.error(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  // 如果修改数据修改失败，回滚事务
            return ResponseResult.no(e.getMessage());   // 返回业务提示信息
        } catch (Exception e) {     // 处理未知异常
            logger.error(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  // 如果修改数据修改失败，回滚事务
            return ResponseResult.no("未知异常");
        }
    }

    /**
     * 获取用户列表
     * 获取区间： [start, min(start+length, total))
     * @param start     开始索引，从0开始
     * @param length    获取长度
     * @return           ResponseResult
     */
    @Override
    public ResponseResult getUsers(int start, int length) {
        try {
            SysUserExample example = new SysUserExample();
            int page = start / length + 1;  // Page 从1开始

            PageHelper.startPage(page, length);     // 设置PageHelper；此处开始分页，获取记录必须放在该语句之后
            List<SysUser> users = sysUserMapper.selectByExample(example);   // 已经被分页

            return ResponseResult.ok(users);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            return ResponseResult.no("未知异常！");
        }
    }

    /**
     * 删除指定id用户
     *
     * @param userId 用户id
     * @return 操作结果
     */
    @Override
    public ResponseResult deleteUser(int userId) {
        try {
            sysUserMapper.deleteByPrimaryKey(userId);
            return ResponseResult.ok();
        } catch (Exception e) {
            logger.error(e);
            return ResponseResult.no("未知异常！");
        }
    }
}
```



# 参考

- [参考一](http://www.cnblogs.com/xusir/p/3650522.html)
- [参考二](http://blog.csdn.net/yipanbo/article/details/46048413)