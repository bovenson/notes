```scala
val getReq: GetRequest = new GetRequest()
println(getReq.getApps)		/// output: null
```

- 对于optional List<>field，new 出来的thrift 实例对应的field，为null