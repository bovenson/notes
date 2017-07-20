# Spark:控制日志输入
## 终端修改
在`pySpark`终端可使用下面命令来改变日志级别
`sc.setLogLevel("WARN")	# 或者INFO等`

## 修改日志设置文件

** 通过调整日志的级别来控制输出的信息量.减少`Spark Shell`使用过程中在终端显示的日志. **
- 切换当前路径到Spark安装路径
- 拷贝一份日志设置文件的模板文件
`cp log4j.properties.template log4j.properties.template`
- 找到下面一行内容
`log4j.rootCategory=INFO, console`
改为如下
`log4j.rootCategory=WARN, console`
