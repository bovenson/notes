---
title: MyBatis 遇到的坑
tags:
	- Java
	- MyBatis
categories:
	- Java
---

# Mapper

```xml
<?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="biz_electrician">
	<!-- 设备解析函数 -->
	<select id="ammeter_parser" parameterType="java.util.HashMap" resultType="java.util.HashMap" >
		SELECT
			*
		FROM	biz_devicegateway_instructions instructions
		INNER JOIN biz_device_elecmeter meter ON meter.gatewayID=instructions.gatewayID AND meter.num=instructions.num
		INNER JOIN biz_type_device devicetype ON devicetype.id = instructions.deviceID
		WHERE
			instructions.gatewayID=#{gatewayID} AND
			instructions.num=#{num}
	</select>

	<!-- 获取多条设备解析函数 -->
	<select id="ammeter_parser_list" parameterType="java.util.HashMap" resultType="java.util.HashMap" >
		SELECT
		*
		FROM	biz_devicegateway_instructions instructions
		INNER JOIN biz_device_elecmeter meter ON meter.gatewayID=instructions.gatewayID AND meter.num=instructions.num
		INNER JOIN biz_type_device devicetype ON devicetype.id = instructions.deviceID
		WHERE
		instructions.gatewayID=#{gatewayID} AND
		instructions.num IN
		<foreach collection="nums"
				 open="(" close=")" separator="," item="num" index="index">
			#{num}
		</foreach>
	</select>
	
</mapper>
```

- `foreach`

  ```xml
  <foreach collection="nums"
           open="(" close=")" separator="," item="num" index="index">
      #{num}
  </foreach>
  ```

  - `collections` : 传入列表（待循环列表）参数的变量名

- `resultType="java.util.HashMap"` :   resultType 指的是 select 返回的每一条记录的类型，而不是所有记录组成的类型