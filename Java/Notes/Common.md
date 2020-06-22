# 对象池

```code
StackObjectPool recMetaRequestPool = new StackObjectPool(new BasePoolableObjectFactory() {
    @Override
    public Object makeObject() throws Exception {
        return new Something();
    }
});
```

# 序列化反序列化

```java
# define structure
public class TestSerialize implements Serializable {
  ...
}
TestSerialize obj = new TestSerialize();

# by apache commons-lang3
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.10</version>
</dependency>

# searialize
byte[] data = SerializationUtils.serialize(obj);

# desearialize
BloomFilter<String> nob = SerializationUtils.deserialize(data);
```

