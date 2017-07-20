# Java随机数

```java
Random random = new Random();	// 实例化对象, 默认种子是当前系统时间的毫秒数
random.nextInt(100)				// 返回[0, 100)内随机数
random.nextInt(100) + 100		// 获取[100, 200)内随机数
random.nextInt()				// 返回随机整数
```

