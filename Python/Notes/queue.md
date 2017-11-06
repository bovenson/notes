[TOC]

# Python queue模块

## 包含队列类型

- `class queue.Queue(maxsize=0)`  FIFO队列
- `class queue.LifoQueue(maxsize=0)` LIFO队列
- `class queue.PriorityQueue(maxsize=0)` 优先队列

### 注意

`maxsize`用来指定队列元素最大个数, 如果设为0, 则表示队列长度没有限制.

## 包含的异常

- `exception queue.Empty`
- `exception queue.Full`

## 定义队列

```python
import queue			# 引入模块
q = queue.Queue()		# 实例化一个队列对象
```

## 操作

### `Queue.qsize()`

获取队列长度. 

**注意:** 返回值是0不保证`get()`操作不会阻塞; 返回值小于定义的最大长度, 不能保证`put()`操作不会阻塞.

### `Queue.empty()`

如果队列为空, 函数返回`True`, 否则返回`False`.

**注意:** 返回值为`True`不能保证`put()`操作不会阻塞; 返回值为`False`不能保证`get()`操作不会阻塞.

### `Queue.full()`

队列长度达到最大值返回`True`, 否则返回`False`.

**注意:** 返回值为`True`不能保证`get()`操作不会阻塞; 返回值为`False`不能保证`put()`操作不会阻塞.

### `Queue.put(item, block=True, timeout=None)`

向队列添加元素.

#### 参数: `item`

要添加的对象.

#### 参数: `block`和`timeout`

##### 如果`block=True timeout=None`

操作会被阻塞, 直到有可用自由槽(free slot).

##### 如果`block=True`并且`timeout`是一个正数

操作会被阻塞, 如果在`timeout`设定的时间内(单位是秒), 没有获取到可用的自由槽, 则会抛出`Queue.Full`异常.

##### 如果`block=Flase`

`timeout`参数会被屏蔽, 且操作不会被堵塞. 如果获取到自由槽, 操作可以正常执行, 否则会抛出`Queue.Full`异常.

### `Queue.put_nowait(item)`

等价于`put(item, false)`.

### `Queue.task_done()`

判断任务是否完成.

### `Queue.join()`

在队列中所有元素处理完之前, `join`方法会阻塞.

## 等待进队操作完成的例子

```python
def worker():
    while True:
        item = q.get()
        if item is None:
            break
        do_work(item)
        q.task_done()

q = queue.Queue()
threads = []
for i in range(num_worker_threads):
    t = threading.Thread(target=worker)
    t.start()
    threads.append(t)

for item in source():
    q.put(item)

# block until all tasks are done
q.join()

# stop workers
for i in range(num_worker_threads):
    q.put(None)
for t in threads:
    t.join()
```

# 原文

```python
The queue module implements multi-producer, multi-consumer queues. It is especially useful in threaded programming when information must be exchanged safely between multiple threads. The Queue class in this module implements all the required locking semantics. It depends on the availability of thread support in Python; see the threading module.

The module implements three types of queue, which differ only in the order in which the entries are retrieved. In a FIFO queue, the first tasks added are the first retrieved. In a LIFO queue, the most recently added entry is the first retrieved (operating like a stack). With a priority queue, the entries are kept sorted (using the heapq module) and the lowest valued entry is retrieved first.

Internally, the module uses locks to temporarily block competing threads; however, it is not designed to handle reentrancy within a thread.

The queue module defines the following classes and exceptions:

class queue.Queue(maxsize=0) 
Constructor for a FIFO queue. maxsize is an integer that sets the upperbound limit on the number of items that can be placed in the queue. Insertion will block once this size has been reached, until queue items are consumed. If maxsize is less than or equal to zero, the queue size is infinite.

class queue.LifoQueue(maxsize=0) 
Constructor for a LIFO queue. maxsize is an integer that sets the upperbound limit on the number of items that can be placed in the queue. Insertion will block once this size has been reached, until queue items are consumed. If maxsize is less than or equal to zero, the queue size is infinite.

class queue.PriorityQueue(maxsize=0) 
Constructor for a priority queue. maxsize is an integer that sets the upperbound limit on the number of items that can be placed in the queue. Insertion will block once this size has been reached, until queue items are consumed. If maxsize is less than or equal to zero, the queue size is infinite.

The lowest valued entries are retrieved first (the lowest valued entry is the one returned by sorted(list(entries))[0]). A typical pattern for entries is a tuple in the form: (priority_number, data).

exception queue.Empty 
Exception raised when non-blocking get() (or get_nowait()) is called on a Queue object which is empty.

exception queue.Full 
Exception raised when non-blocking put() (or put_nowait()) is called on a Queue object which is full.

17.7.1. Queue Objects
Queue objects (Queue, LifoQueue, or PriorityQueue) provide the public methods described below.

Queue.qsize() 
Return the approximate size of the queue. Note, qsize() > 0 doesn’t guarantee that a subsequent get() will not block, nor will qsize() < maxsize guarantee that put() will not block.

Queue.empty() 
Return True if the queue is empty, False otherwise. If empty() returns True it doesn’t guarantee that a subsequent call to put() will not block. Similarly, if empty() returns False it doesn’t guarantee that a subsequent call to get() will not block.

Queue.full() 
Return True if the queue is full, False otherwise. If full() returns True it doesn’t guarantee that a subsequent call to get() will not block. Similarly, if full() returns False it doesn’t guarantee that a subsequent call to put() will not block.

Queue.put(item, block=True, timeout=None) 
Put item into the queue. If optional args block is true and timeout is None (the default), block if necessary until a free slot is available. If timeout is a positive number, it blocks at most timeout seconds and raises the Full exception if no free slot was available within that time. Otherwise (block is false), put an item on the queue if a free slot is immediately available, else raise the Full exception (timeout is ignored in that case).

Queue.put_nowait(item) 
Equivalent to put(item, False).

Queue.get(block=True, timeout=None) 
Remove and return an item from the queue. If optional args block is true and timeout is None (the default), block if necessary until an item is available. If timeout is a positive number, it blocks at most timeout seconds and raises the Empty exception if no item was available within that time. Otherwise (block is false), return an item if one is immediately available, else raise the Empty exception (timeout is ignored in that case).

Queue.get_nowait() 
Equivalent to get(False).

Two methods are offered to support tracking whether enqueued tasks have been fully processed by daemon consumer threads.

Queue.task_done() 
Indicate that a formerly enqueued task is complete. Used by queue consumer threads. For each get() used to fetch a task, a subsequent call to task_done() tells the queue that the processing on the task is complete.

If a join() is currently blocking, it will resume when all items have been processed (meaning that a task_done() call was received for every item that had been put() into the queue).

Raises a ValueError if called more times than there were items placed in the queue.

Queue.join() 
Blocks until all items in the queue have been gotten and processed.

The count of unfinished tasks goes up whenever an item is added to the queue. The count goes down whenever a consumer thread calls task_done() to indicate that the item was retrieved and all work on it is complete. When the count of unfinished tasks drops to zero, join() unblocks.

Example of how to wait for enqueued tasks to be completed:

def worker():
    while True:
        item = q.get()
        if item is None:
            break
        do_work(item)
        q.task_done()

q = queue.Queue()
threads = []
for i in range(num_worker_threads):
    t = threading.Thread(target=worker)
    t.start()
    threads.append(t)

for item in source():
    q.put(item)

# block until all tasks are done
q.join()

# stop workers
for i in range(num_worker_threads):
    q.put(None)
for t in threads:
    t.join()
```

