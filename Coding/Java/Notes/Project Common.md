# Resource

## 读取文件

```java
// read resource file
ClassLoader classloader = Thread.currentThread().getContextClassLoader();
InputStream is = classloader.getResourceAsStream("name.ext");
InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
BufferedReader br = new BufferedReader(streamReader);
String st;
while((st = br.readLine()) != null){
  // do something
}
```

# Validate

```java
// guava
Preconditions.*(...);

// apache common
Validate.*(...);
```

