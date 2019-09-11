# Http Request

```java
// construct
RequestConfig requestConfig = RequestConfig.custom()
  .setConnectTimeout(100)
  .setSocketTimeout(100)
  .setConnectionRequestTimeout(100)
  .build();
HttpClient httpClient = HttpClients.createDefault();

// post
String url = "http://localhost";
HttpPost request = new HttpPost(url);
request.setConfig(requestConfig);

HttpEntity entity = MultipartEntityBuilder
        .create()
        .addTextBody("uid", "uid")
        .addTextBody("data", "data")
        .build();

request.setEntity(entity);

// get
URIBuilder builder = new URIBuilder("http://localhost");
builder.setParameter("uid", "uid")
 	 			.setParameter("data", "data");
HttpGet request = new HttpGet(builder.build());
request.setConfig(requestConfig);
httpClient.execute(request);
```



