# RESTful 

-   **REST(Representational State Transfer,表述性状态转移)**
-   REST指的是一组架构约束条件和原则,如果一个架构符合REST的约束条件和原则,我们就称它为RESTful架构

## 统一资源接口

RESTful架构应该遵循统一的接口原则,统一接口包含了一组受限的预定义的操作,不论什么样的资源,都是通过使用相同的接口进行资源的访问.

GET,DELETE,PUT,POST的典型用法:

### GET

-   安全且幂等
-   获取表示
-   变更时获取表示(缓存)
-   200(OK) - 表示已在响应中发出
-   204(无内容) - 资源有空表示
-   301(Moved Permanently) - 资源的URI已被更新
-   303(See Other) - 其他(如, 负载均衡)
-   304(not modified) - 资源未更改(缓存)
-   400(bad request) - 坏请求(如,参数错误)
-   404(not found) - 资源不存在
-   406(not acceptable) - 服务端不支持所需表示
-   500(internal server error) - 通用错误响应
-   503(Service Unavailable) - 服务端当前无法处理请求

### POST

-   不安全且不幂等
-   使用服务器管理的(自动生成)实例号创建资源
-   创建子资源
-   部分更新资源
-   如果没有被修改,则不更新资源(乐观锁)
-   200(OK) - 如果现有资源已被更改
-   201(created) - 如果新资源被创建
-   202(accepted) - 已接受处理请求但尚未完成(异步处理)
-   301(Moved Permanently) - 资源的URI已被更新
-   303(See Other) - 其他(如, 负载均衡)
-   304(not modified) - 资源未更改(缓存)
-   400(bad request) - 坏请求(如,参数错误)
-   404(not found) - 资源不存在
-   406(not acceptable) - 服务端不支持所需表示
-   409(conflict) - 通用冲突
-   412(Precondition Failed) - 前置条件失败(如执行条件更新时的冲突)
-   415(unsupported media type) - 接收到的表示不受支持
-   500(internal server error) - 通用响应错误
-   503(Service Unavaliable) - 服务当前无法处理请求

### PUT

-   不安全但幂等
-   用客户端管理的实例号创建一个资源
-   通过替换的方式更新资源
-   如果未被修改，则更新资源（乐观锁）
-   200 (OK) - 如果已存在资源被更改
-   201 (created) - 如果新资源被创建
-   301(Moved Permanently) - 资源的URI已更改
-   303 （See Other）- 其他（如，负载均衡）
-   400 （bad request）- 指代坏请求
-   404 （not found）- 资源不存在
-   406 （not acceptable）- 服务端不支持所需表示
-   409 （conflict）- 通用冲突
-   412 （Precondition Failed）- 前置条件失败（如执行条件更新时的冲突）
-   415 （unsupported media type）- 接受到的表示不受支持
-   500 （internal server error）- 通用错误响应
-   503 （Service Unavailable）- 服务当前无法处理请求

### DELETE

-   不安全但幂等
-   删除资源
-   200 （OK）- 资源已被删除
-   301 （Moved Permanently）- 资源的URI已更改
-   303 （See Other）- 其他，如负载均衡
-   400 （bad request）- 指代坏请求
-   404 （not found）- 资源不存在
-   409 （conflict）- 通用冲突
-   500 （internal server error）- 通用错误响应
-   503 （Service Unavailable）- 服务端当前无法处理请求

## 常见问题

### POST和PUT用于创建资源时有什么区别?

POST和PUT在创建资源的区别在于，所创建的资源的名称(URI)是否由客户端决定。 例如为我的博文增加一个java的分类，生成的路径就是分类名/categories/java，那么就可以采用PUT方法。不过很多人直接把POST、GET、PUT、DELETE直接对应上CRUD，例如在一个典型的rails实现的RESTful应用中就是这么做的。
我认为，这是因为rails默认使用服务端生成的ID作为URI的缘故，而不少人就是通过rails实践REST的，所以很容易造成这种误解。

### 客户端不一定都支持这些HTTP方法吧?

的确有这种情况，特别是一些比较古老的基于浏览器的客户端，只能支持GET和POST两种方法。 在实践上，客户端和服务端都可能需要做一些妥协。例如rails框架就支持通过隐藏参数\_method=DELETE来传递真实的请求方法， 而像Backbone这样的客户端MVC框架则允许传递\_method传输和设置X-HTTP-Method-Override头来规避这个问题。