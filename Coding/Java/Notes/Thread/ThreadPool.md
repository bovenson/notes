---
title: Java Thread Pool
---

# 简介

Java的Executors库提供四种线程池：

- newCachedThreadPool
  - 创建一个可缓存线程池
  - 如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
  
- newFixedThreadPool
  - 创建一个定长线程池
  - 可控制线程最大并发数，超出的线程会在队列中等待。
  
- newScheduledThreadPool
  - 创建一个定长线程池
  - 支持定时及周期性任务执行
  
- newSingleThreadExecutor
  - 创建一个单线程化的线程池
  - 它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
  
- newWorkStealingPool

  - 减少工作队列上的争用

  - > 典型的线程池，只有一个队列，每个线程在获取task的时候会对队列执行加锁、解锁操作。如果任务很简单，且数量很多，那么花费在队列同步控制的时间会更多。使用无锁队列有帮助，但是不能完全解决问题。
    >
    > work stealing是指，每个线程有自己的队列，当该线程任务队列没有任务时，去其他线程队列偷取任务，这样可以有效减少队列同步耗费的时间。

# Customer Thread Pool

- LinkedBlockingQueue
  - 无界，无界缓存等待队列
  - 基于链表的阻塞队列，内部维持着一个数据缓冲队列
  - 当队列缓冲区**未达到**最大值缓存容量时，生产者立即返回，不阻塞
  - 当队列缓冲区**达到**最大值缓存容量时，阻塞，直到有消费者消费
- SynchronousQueue
  - 无界的，无缓冲等待队列
  - 在某次添加元素后必须等待其他线程取走后才能继续添加
  - 可以认为SynchronousQueue是一个缓存值为1的阻塞队列，但是 isEmpty()方法永远返回是true，remainingCapacity() 方法永远返回是0，remove()和removeAll() 方法永远返回是false，iterator()方法永远返回空，peek()方法永远返回null
- ArrayBlockingQueue
  - 有界，有界缓存的等待队列
  - 基于数组的阻塞队列，同LinkedBlockingQueue类似，内部维持着一个定长数据缓冲队列（该队列由数组构成）
  - ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行，这点尤其不同于LinkedBlockingQueue；按照实现原理来分析，ArrayBlockingQueue完全可以采用分离锁，从而实现生产者和消费者操作的完全并行运行。Doug Lea之所以没这样去做，也许是因为ArrayBlockingQueue的数据写入和获取操作已经足够轻巧，以至于引入独立的锁机制，除了给代码带来额外的复杂性外，其在性能上完全占不到任何便宜。 ArrayBlockingQueue和LinkedBlockingQueue间还有一个明显的不同之处在于，前者在插入或删除元素时不会产生或销毁任何额外的对象实例，而后者则会生成一个额外的Node对象。这在长时间内需要高效并发地处理大批量数据的系统中，其对于GC的影响还是存在一定的区别

# Usage

## Create

```java
ExecutorService executorService = Executors.newFixedThreadPool(512);
```

## Usage

### Submit Single Task & Wait

```shell
ExecutorService executorService = Executors.newFixedThreadPool(512);
Future<Void> f = executorService.submit(() -> { [do someting] });
f.get(1100, TimeUnit.MILLISECONDS);
```

