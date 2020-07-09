# toMap

```java
List<String> ids = Lists.newArrayList("A", "B");
Map<String, String> res = ids.stream().collect(Collectors.toMap(c -> c, c -> c));
System.out.println(res);

/* output
{A=A, B=B}
*/
```

