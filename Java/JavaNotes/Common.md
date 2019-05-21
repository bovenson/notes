# 对象池

```code
StackObjectPool recMetaRequestPool = new StackObjectPool(new BasePoolableObjectFactory() {
    @Override
    public Object makeObject() throws Exception {
        return new Something();
    }
});
```

