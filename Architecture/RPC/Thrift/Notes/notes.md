# Set fields

```java
for (Article._Fields field : Article._Fields.values()) {
	if (from.isSet(field)) {
		to.setFieldValue(field, from.getFieldValue(field));
	}
}
```

