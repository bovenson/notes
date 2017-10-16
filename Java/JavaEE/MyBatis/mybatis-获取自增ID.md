---
title: Mybatis获取自增ID
tags: Mybaties获取自增ID
---

# 配置

## 相关

```xml
<table schema="" tableName="biz_customer">
  <generatedKey column="id" sqlStatement="MySql" identity="true"/>	<!-- 设置可以获取自增ID -->
</table>
```

## 全部

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <classPathEntry location="mysql-connector-java-5.1.41-bin.jar" />   
	<context id="mysql" targetRuntime="MyBatis3">
        <!-- <plugin type="org.mybatis.generator.plugins.EqualsHashCodePlugin" />
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />
        <plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin" />
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin" />
        <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"/>
        <plugin type="org.mybatis.generator.plugins.CaseInsensitiveLikePlugin"/> -->
        
        <commentGenerator>
			<!-- 是否去除自动生成的注释 true：是 ： false:否 -->
			<property name="suppressAllComments" value="true" />
		</commentGenerator>
		<!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
		<jdbcConnection driverClass="com.mysql.jdbc.Driver"
			connectionURL="jdbc:mysql://202.118.26.156:3306/power_cloud?characterEncoding=utf8" 
			userId="-------"
			password="-------">
		</jdbcConnection>
		<!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 和 
			NUMERIC 类型解析为java.math.BigDecimal -->
		<javaTypeResolver>
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>

		<!-- targetProject:生成PO类的位置 -->
		<javaModelGenerator targetPackage="com.neu.cse.powercloud.pojo.sysmanage"
			targetProject="./power_cloud">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
			<!-- 从数据库返回的值被清理前后的空格 -->
			<property name="trimStrings" value="false" />
		</javaModelGenerator>
        <!-- targetProject:mapper映射文件生成的位置 -->
		<sqlMapGenerator targetPackage="com.neu.cse.powercloud.mapper.sysmanage" 
			targetProject="./power_cloud">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
		</sqlMapGenerator>
		<!-- targetPackage：mapper接口生成的位置 -->
		<javaClientGenerator type="XMLMAPPER"
			targetPackage="com.neu.cse.powercloud.mapper.sysmanage" 
			targetProject="./power_cloud">
			<!-- enableSubPackages:是否让schema作为包的后缀 -->
			<property name="enableSubPackages" value="false" />
		</javaClientGenerator>
		
		<!-- 指定数据库表 -->
		<table schema="" tableName="sys_user">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="sys_role">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="sys_user_role">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="sys_function">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="sys_role_function">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="sys_data_autority">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="sys_user_data_auth">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
		<table schema="" tableName="biz_customer">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
		</table>
	</context>
</generatorConfiguration>
```

# Java代码

```java
SysUserExample example = new SysUserExample();
SysUserExample.Criteria criteria = example.createCriteria();
criteria.andUsernameEqualTo(user.getUsername());
if (sysUserMapper.selectByExample(example).isEmpty()) {     // 判断用户名是否已存在
  try {
    user.setPassword(MD5Encoder.encoderStringByMD5(user.getPassword()));    // 对密码加密
    sysUserMapper.insert(user);     // 向数据库插入新用户信息
    logger.debug("新插入记录自增ID:" + user.getId());	// 获取自增ID
  } catch (Exception e) {
    throw new ServiceRuntimeException("用户信息错误");
  }

  // 设置角色
  // TODO
  // 设置数据权限
  // TODO

  // throw new ServiceRuntimeException("回滚测试");

  return ResponseResult.ok();
} else {
  throw new ServiceRuntimeException("用户已存在");
}
```

# 测试输出

```shell
/usr/lib/jvm/jdk/bin/java ...
2017-10-16 12:32:26,135 [main] [com.neu.cse.powercloud.serviceImpl.sysmanage.UserManageServiceImpl]-[DEBUG] 新插入记录自增ID:100000023

Process finished with exit code 0
```



# 参考

- [参考一](http://dunnohe.github.io/2016/07/22/Java-mybatis%E8%BF%94%E5%9B%9E%E4%B8%BB%E9%94%AE%E7%9A%84%E5%9D%91/)