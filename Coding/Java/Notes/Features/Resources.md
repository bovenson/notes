# Closeable & AutoCloseable & Flushable

对于Stream类，有三个接口至关重要。Closeable、Flushable在java.io中定义，在JDK 5中添加。AutoCloseable在JDK 7中添加，在包java.lang中定义。

AutoCloseable提供`try-with-resources`语句支持，使资源关闭过程自动化，只有继承该接口的类才能被`try-with-resources`管理。从JDK 7开始，Closeable扩展了AutoCloseable。因此，任何实现Closeable的类也将实现AutoCloseable。

`try-with-resources` 内声明的资源，隐式声明为final，定义多个资源使用分号分隔。