# Get request body

```scala
/// paoding rose param resolver
class RequestDataResolver extends ParamResolver {
  private final val logger = LoggerFactory.getLogger(this.getClass)

  override def supports(metaData: ParamMetaData): Boolean = metaData.getParamType.equals(classOf[ClsName])

  override def resolve(inv: Invocation, metaData: ParamMetaData): AnyRef = {
    val gson = new Gson()
    /// 获取 request body
    // val requestBody = inv.getRequest.getReader.lines.collect(Collectors.joining(System.lineSeparator))
    gson.fromJson(inv.getRequest.getReader, classOf[ClsName])
  }
}
```

