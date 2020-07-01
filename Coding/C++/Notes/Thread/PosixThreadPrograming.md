# 线程创建与取消

## 创建线程

```c++
int pthread_create(pthread_t * thread, 
                   pthread_attr_t * attr,  
                   void * (*start_routine)(void *), void * arg)
```

**线程创建属性**

- `__detachstate`
  - 新线程是否与进程中其他线程脱离同步
  - 如果置位则新线程不能用pthread_join()来同步，且在退出时自行释放所占用的资源
  - 缺省为pthread_CREATE_JOINABLE状态
  - 这个属性也可以在线程创建并运行以后用pthread_detach()来设置
  - 一旦设置为pthread_CREATE_DETACH状态（不论是创建时设置还是运行时设置）则不能再恢复到pthread_CREATE_JOINABLE状态
- `__schedpolicy`
  - 新线程的调度策略
    - SCHED_OTHER（正常、非实时）
    - SCHED_RR（实时、轮转法）
    - SCHED_FIFO（实时、先入先出）
  - 后两种调度策略仅对超级用户有效
  - 运行时可以用pthread_setschedparam()来改变
- `__schedparam`
- `__inheritsched`
- `__scope`
  - 表示线程间竞争CPU的范围，也就是说线程优先级的有效范围

**线程创建实现**

- 线程的管理（创建、同步、删除操作）在核外pthread库中进行
- 调用内核提供的 `do_fork()` 接口创建线程实体，通过指定参数有选择地复制进程数据（共享内存、共享文件系统信息、共享文件描述符表、共享信息句柄表等）

## 线程取消

**条件**

- 主体函数退出
- 线程主动退出
- 接收到终止信号

**取消点**

- 引起阻塞系统的调用
  - pthread_join()
  - pthread_testcancel()
  - pthread_cond_wait()
  - pthread_cond_timedwait()
  - sem_wait()
  - sigwait()
  - read()
  - write()

**PS**

- 对于没有取消点的无限循环，可能无法接受到传入的取消信号，可以添加`pthread_testcancel()`调用

  ```c++
  pthread_testcancel();  
  ```

# 互斥锁

## 创建

- 静态方式

  ```c++
  pthread_mutex_t mutex = pthread_MUTEX_INITIALIZER;
  ```

  - `pthread_mutex_t`：结构体
  - `pthread_MUTEX_INITIALIZER` ： 结构常量

- 动态方式

  ```c++
  int pthread_mutex_init(pthread_mutex_t *mutex, const pthread_mutexattr_t *mutexattr) 
  ```

## 注销

```c++
int pthread_mutex_destroy(pthread_mutex_t *mutex) 
```

## 锁类型属性

- `pthread_MUTEX_TIMED_NP`
  - 缺省值
  - 当一个线程加锁以后，其余请求锁的线程将形成一个等待队列，并在解锁后按优先级获得锁
  - 这种锁策略保证了资源分配的公平性
- `pthread_MUTEX_RECURSIVE_NP`
  - 嵌套锁
  - 允许同一个线程对同一个锁成功获得多次，并通过多次unlock解锁
  - 如果是不同线程请求，则在加锁线程解锁时重新竞争
- `pthread_MUTEX_ERRORCHECK_NP`
  - 检错锁
  - 如果同一个线程请求同一个锁，则返回EDEADLK，否则与pthread_MUTEX_TIMED_NP类型动作相同
  - 这样就保证当不允许多次加锁时不会出现最简单情况下的死锁
- `pthread_MUTEX_ADAPTIVE_NP`
  - 适应锁
  - 动作最简单的锁类型，仅等待解锁后重新竞争

## 锁操作

- 加锁

  ```c++
  int pthread_mutex_lock(pthread_mutex_t *mutex)
  ```

- 解锁

  ```c++
  int pthread_mutex_unlock(pthread_mutex_t *mutex)  
  ```

- 测试加锁

  ```c++
  int pthread_mutex_trylock(pthread_mutex_t *mutex)  
  ```

  - 在锁已经被占据时，返回`EBUSY`，而不是挂起

## PS

- 如果在加锁后解锁前被取消，锁将永远保持锁定状态
  - 这种情况下，需要在线程退出回调函数中解锁
- 锁机制不是异步信号安全的
  - 也就是说，不应该在信号处理过程中使用互斥锁，否则容易造成死锁

# 条件变量

条件变量是利用线程间共享的全局变量进行同步的一种机制。

主要包括两个动作：

- 一个线程等待"条件变量的条件成立"而挂起
- 另一个线程使"条件成立"（给出条件成立信号）。

为了防止竞争，条件变量的使用总是和一个互斥锁结合在一起。  

## 创建

- 静态方式

  ```c++
  pthread_cond_t cond=pthread_COND_INITIALIZER
  ```

- 动态方式

  ```c++
  int pthread_cond_init(pthread_cond_t *cond, pthread_condattr_t *cond_attr)  
  ```

## 注销

只有在没有线程在该条件变量上等待的时候才能注销这个条件变量，否则返回EBUSY。

```c++
int pthread_cond_destroy(pthread_cond_t *cond)  
```

## 等待

```c++
// 无条件等待
int pthread_cond_wait(pthread_cond_t *cond, pthread_mutex_t *mutex)  
    
// 指定Timeout
int pthread_cond_timedwait(pthread_cond_t *cond, pthread_mutex_t *mutex, const struct timespec *abstime)  
```

无论哪种等待方式，都必须和一个互斥锁配合，以防止多个线程同时请求pthread_cond_wait()（或pthread_cond_timedwait()，下同）的竞争条件（Race Condition）。mutex互斥锁必须是普通锁（pthread_MUTEX_TIMED_NP）或者适应锁（pthread_MUTEX_ADAPTIVE_NP），且在调用pthread_cond_wait()前必须由本线程加锁（pthread_mutex_lock()），而在更新条件等待队列以前，mutex保持锁定状态，并在线程挂起进入等待前解锁。在条件满足从而离开pthread_cond_wait()之前，mutex将被重新加锁，以与进入pthread_cond_wait()前的加锁动作对应。 

## 激发

激发条件有两种形式，pthread_cond_signal()激活一个等待该条件的线程，存在多个等待线程时按入队顺序激活其中一个；而pthread_cond_broadcast()则激活所有等待线程。  

## 示例

```c++
#include <stdio.h>  
#include <pthread.h>  
#include <unistd.h>  

pthread_mutex_t mutex;  
pthread_cond_t cond;  

void * child1(void *arg)  
{  
    pthread_cleanup_push(pthread_mutex_unlock,&mutex); /* comment 1 */  
    while(1){  
        printf("thread 1 get running \n");  
        printf("thread 1 pthread_mutex_lock returns %d\n",  
        pthread_mutex_lock(&mutex));  
        pthread_cond_wait(&cond,&mutex);  
        printf("thread 1 condition applied\n");  

        pthread_mutex_unlock(&mutex);  
        sleep(5);  
    }  
    pthread_cleanup_pop(0); /* comment 2 */  
}  

void *child2(void *arg)  
{  
    while(1){  
        sleep(3); /* comment 3 */  
        printf("thread 2 get running.\n");  
        printf("thread 2 pthread_mutex_lock returns %d\n",  
        pthread_mutex_lock(&mutex));  
        pthread_cond_wait(&cond,&mutex);  
        printf("thread 2 condition applied\n");  
        pthread_mutex_unlock(&mutex);  
        sleep(1);  
    }  
}  

int main(void)  
{  
    int tid1,tid2;  

    printf("hello, condition variable test\n");  
    pthread_mutex_init(&mutex,NULL);  
    pthread_cond_init(&cond,NULL);  
    pthread_create(&tid1,NULL,child1,NULL);  
    pthread_create(&tid2,NULL,child2,NULL);  
    do{  
        sleep(2); /* comment 4 */  
        pthread_cancel(tid1); /* comment 5 */  
        sleep(2); /* comment 6 */  
        pthread_cond_signal(&cond);  
    } while(1);  
    sleep(100);  
    pthread_exit(0);  
}  
```

# 信号灯

信号灯与互斥锁和条件变量的主要不同在于"灯"的概念，灯亮则意味着资源可用，灯灭则意味着不可用。如果说后两中同步方式侧重于"等待"操作，即资源不可用的话，信号灯机制则侧重于点灯，即告知资源可用；没有等待线程的解锁或激发条件都是没有意义的，而没有等待灯亮的线程的点灯操作则有效，且能保持灯亮状态。当然，这样的操作原语也意味着更多的开销。  

信号灯的应用除了灯亮/灯灭这种二元灯以外，也可以采用大于1的灯数，以表示资源数大于1，这时可以称之为多元灯。 

## 创建

```c++
int sem_init(sem_t *sem, int pshared, unsigned int value)  
```

- `value`：信号灯的初值 
- `pshared`：是否为多进程共享而不仅仅是用于一个进程
  - LinuxThreads没有实现多进程共享信号灯，因此所有非0值的pshared输入都将使sem_init()返回-1，且置errno为ENOSYS。

## 注销

```c++
int sem_destroy(sem_t * sem)  
```

被注销的信号灯sem要求已没有线程在等待该信号灯，否则返回-1，且置errno为EBUSY。除此之外，LinuxThreads的信号灯注销函数不做其他动作。

## 电灯

```c++
int sem_post(sem_t * sem)  
```

点灯操作将信号灯值原子地加1，表示增加一个可访问的资源。  

## 灭灯

```c++
int sem_wait(sem_t * sem)  
int sem_trywait(sem_t * sem)  
```

sem_wait()为等待灯亮操作，等待灯亮（信号灯值大于0），然后将信号灯原子地减1，并返回。

sem_trywait()为sem_wait()的非阻塞版，如果信号灯计数大于0，则原子地减1并返回0，否则立即返回-1，errno置为EAGAIN。

## 获取灯值

```c++
int sem_getvalue(sem_t * sem, int * sval)  
```

读取sem中的灯计数，存于*sval中，并返回0。  

## PS

- sem_post()是唯一能用于异步信号处理函数的POSIX异步信号安全的API

# 异步信号

由于LinuxThreads是在核外使用核内轻量级进程实现的线程，所以基于内核的异步信号操作对于线程也是有效的。但同时，由于异步信号总是实际发往某个进程，所以无法实现POSIX标准所要求的"信号到达某个进程，然后再由该进程将信号分发到所有没有阻塞该信号的线程中"原语，而是只能影响到其中一个线程。 

POSIX异步信号同时也是一个标准C库提供的功能，主要包括信号集管理（sigemptyset()、sigfillset()、sigaddset()、sigdelset()、sigismember()等）、信号处理函数安装（sigaction()）、信号阻塞控制（sigprocmask()）、被阻塞信号查询（sigpending()）、信号等待(sigsuspend())等，它们与发送信号的kill()等函数配合就能实现进程间异步信号功能。LinuxThreads围绕线程封装了sigaction()何raise()。

LinuxThreads中扩展的异步信号函数，包括pthread_sigmask()、pthread_kill()和sigwait()三个函数。毫无疑问，所有POSIX异步信号函数对于线程都是可用的。  

- `pthread_sigmask`

  ```c++
  int pthread_sigmask(int how, const sigset_t *newmask, sigset_t *oldmask)  
  ```

  - 设置线程的信号屏蔽码，语义与sigprocmask()相同，但对不允许屏蔽的Cancel信号和不允许响应的Restart信号进行了保护。被屏蔽的信号保存在信号队列中，可由sigpending()函数取出。  

- `pthread_kill`

  ```c++
  int pthread_kill(pthread_t thread, int signo)  
  ```

  - 向thread号线程发送signo信号。实现中在通过thread线程号定位到对应进程号以后使用kill()系统调用完成发送。  

- `sigwait`

  ```c++
  int sigwait(const sigset_t *set, int *sig)
  ```

  - 挂起线程，等待set中指定的信号之一到达，并将到达的信号存入*sig中。POSIX标准建议在调用sigwait()等待信号以前，进程中所有线程都应屏蔽该信号，以保证仅有sigwait()的调用者获得该信号，因此，对于需要等待同步的异步信号，总是应该在创建任何线程以前调用pthread_sigmask()屏蔽该信号的处理。而且，调用sigwait()期间，原来附接在该信号上的信号处理函数不会被调用。  

# 其他同步方式

- 基于文件系统的IPC
  - 管道
  - Unix域Socket
- 消息队列
- System V的信号灯

# 参考

- [Ref 1](https://blog.csdn.net/lvfengchang1220/article/details/38340757)