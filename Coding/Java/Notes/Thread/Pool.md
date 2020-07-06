# Create

```java
ExecutorService executorService = Executors.newFixedThreadPool(512);
```

# Usage

## Submit Single Task & Wait

```shell
ExecutorService executorService = Executors.newFixedThreadPool(512);
Future<Void> f = executorService.submit(() -> { [do someting] });
f.get(1100, TimeUnit.MILLISECONDS);
```

