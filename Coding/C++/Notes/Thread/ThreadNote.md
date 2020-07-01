# **C++11 之前**

```c++
#include <pthread.h>
pthread_create (thread, attr, start_routine, arg) 
```

| 参数          | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| thread        | 子例程返回的新线程的不透明的唯一标识符。                     |
| attr          | 一个opaque属性对象，可用于设置线程属性。您可以指定线程属性对象，或者为默认值指定NULL。 |
| start_routine | 线程创建后将执行的C ++例程。                                 |
| arg           | 可以传递给start_routine的单个参数。它必须通过引用传递为void类型的指针转换。如果不传递参数，则可以使用NULL。 |

# **C++11**

```c++
#include <thread>
std::thread nth(&ctr::Class::func, this);		// 创建运行对象成员函数的线程
std::thread nth(func, "HELLO");
```

## **构造函数**

| 构造函数                                                     | 说明                        | 备注                                                         |
| ------------------------------------------------------------ | --------------------------- | ------------------------------------------------------------ |
| thread() noexcept;                                           | 默认构造函数                | 创建空的std::thread执行对象                                  |
| template <class Fn, class... Args> explicit thread (Fn&& fn, Args&&... args); | 初始化                      | 创建std::thread对象，该对象可被joinable，信产生的线程会调用fn函数，该函数的参数由args给出 |
| thread (const thread&) = delete;                             | 拷贝构造（被禁用 - delete） | 意味着std::thread对象不可被拷贝                              |
| thread (thread&& x) noexcept;                                | 移动构造                    | 调用成功之后x不代表任何std::thread执行对象                   |

**Example**

```c++
#include <iostream>
#include <utility>
#include <thread>
#include <chrono>
#include <functional>
#include <atomic>

void f1(int n)
{
    for (int i = 0; i < 5; ++i) {
        std::cout << "Thread " << n << " executing\n";
        std::this_thread::sleep_for(std::chrono::milliseconds(10));
    }
}

void f2(int& n)
{
    for (int i = 0; i < 5; ++i) {
        std::cout << "Thread 2 executing\n";
        ++n;
        std::this_thread::sleep_for(std::chrono::milliseconds(10));
    }
}

int main()
{
    int n = 0;
    std::thread t1; // t1 is not a thread
    std::thread t2(f1, n + 1); // pass by value
    std::thread t3(f2, std::ref(n)); // pass by reference
    std::thread t4(std::move(t3)); // t4 is now running f2(). t3 is no longer a thread
    t2.join();
    t4.join();
    std::cout << "Final value of n is " << n << '\n';
}
```

```c++
#include <iostream>
#include <unistd.h>
#include <thread>
#include <unistd.h>

using namespace std;

class A {
public:
    void f(string str) {
        cout << "A::f() " << str << endl;
        sleep(3);
    }

    void fn() {
        cout << "A::fn()" << endl;
        sleep(3);
    }
};

void f(string str) {
    cout << "f(string str)" << str << endl;
    sleep(3);
}

void fn() {
    cout << "fn()" << endl;
    sleep(3);
}

int main() {
    A a;
    std::thread nth(f, "Hello");		// 有参数的方法
    sleep(1);
    std::thread nfth(fn);				// 无参方法
    sleep(1);
    std::thread nfthc(&A::f, &a, "hello");	// 有参类方法

    while (true) {}
    return 0;
}
/** Output
f(string str)Hello
fn()
A::f() hello
*/
```

```c++
// constructing threads
#include <iostream>       // std::cout
#include <atomic>         // std::atomic
#include <thread>         // std::thread
#include <vector>         // std::vector

std::atomic<int> global_counter (0);

void increase_global (int n) { for (int i=0; i<n; ++i) ++global_counter; }

void increase_reference (std::atomic<int>& variable, int n) { for (int i=0; i<n; ++i) ++variable; }

struct C : std::atomic<int> {
  C() : std::atomic<int>(0) {}
  void increase_member (int n) { for (int i=0; i<n; ++i) fetch_add(1); }
};

int main ()
{
  std::vector<std::thread> threads;

  std::cout << "increase global counter with 10 threads...\n";
  for (int i=1; i<=10; ++i)
    threads.push_back(std::thread(increase_global,1000));

  std::cout << "increase counter (foo) with 10 threads using reference...\n";
  std::atomic<int> foo(0);
  for (int i=1; i<=10; ++i)
    threads.push_back(std::thread(increase_reference,std::ref(foo),1000));

  std::cout << "increase counter (bar) with 10 threads using member...\n";
  C bar;
  for (int i=1; i<=10; ++i)
    threads.push_back(std::thread(&C::increase_member,std::ref(bar),1000));

  std::cout << "synchronizing all threads...\n";
  for (auto& th : threads) th.join();

  std::cout << "global_counter: " << global_counter << '\n';
  std::cout << "foo: " << foo << '\n';
  std::cout << "bar: " << bar << '\n';

  return 0;
}
```

## 赋值操作

| 赋值                                       | 说明                   | 备注                                                         |
| ------------------------------------------ | ---------------------- | ------------------------------------------------------------ |
| thread& operator=(thread&& rhs) noexcept;  | Move 赋值操作          | 如果当前对象不可 `joinable`，需要传递一个右值引用(`rhs`)给 `move` 赋值操作；如果当前对象可被 `joinable`，则会调用 `terminate`() 报错。 |
| thread& operator=(const thread&) = delete; | 拷贝赋值操作 [deleted] | 被禁用，因此 `std::thread` 对象不可拷贝赋值。                |

```c++
#include <stdio.h>
#include <stdlib.h>

#include <chrono>    // std::chrono::seconds
#include <iostream>  // std::cout
#include <thread>    // std::thread, std::this_thread::sleep_for

void thread_task(int n) {
    std::this_thread::sleep_for(std::chrono::seconds(n));
    std::cout << "hello thread "
        << std::this_thread::get_id()
        << " paused " << n << " seconds" << std::endl;
}

int main(int argc, const char *argv[])
{
    std::thread threads[5];
    std::cout << "Spawning 5 threads...\n";
    for (int i = 0; i < 5; i++) {
        threads[i] = std::thread(thread_task, i + 1);
    }
    std::cout << "Done spawning threads! Now wait for them to join\n";
    for (auto& t: threads) {
        t.join();
    }
    std::cout << "All threads joined.\n";

    return EXIT_SUCCESS;
}
```

## 其他成员函数

- `get_id()`：获取线程ID

- `joinable()`：检查线程是否可被join

  - 检查当前的线程对象是否表示了一个活动的执行线程，由默认构造函数创建的线程是不能被 join 的
  - 如果某个线程 已经执行完任务，但是没有被 join 的话，该线程依然会被认为是一个活动的执行线程，因此也是可以被 join 的

- `detach`：将当前线程对象所代表的执行实例与该线程对象分离，使得线程的执行可以单独进行。一旦线程执行完毕，它所分配的资源将会被释放。

- `swap`：交换两个线程对象所代表的底层句柄(underlying handles)

- `native_handle`：返回 native handle

  ```c++
  #include <thread>
  #include <iostream>
  #include <chrono>
  #include <cstring>
  #include <pthread.h>
  
  std::mutex iomutex;
  void f(int num)
  {
    std::this_thread::sleep_for(std::chrono::seconds(1));
  
   sched_param sch;
   int policy; 
   pthread_getschedparam(pthread_self(), &policy, &sch);
   std::lock_guard<std::mutex> lk(iomutex);
   std::cout << "Thread " << num << " is executing at priority "
             << sch.sched_priority << '\n';
  }
  
  int main()
  {
    std::thread t1(f, 1), t2(f, 2);
  
    sched_param sch;
    int policy; 
    pthread_getschedparam(t1.native_handle(), &policy, &sch);
    sch.sched_priority = 20;
    if(pthread_setschedparam(t1.native_handle(), SCHED_FIFO, &sch)) {
        std::cout << "Failed to setschedparam: " << std::strerror(errno) << '\n';
    }
  
    t1.join();
    t2.join();
  }
  ```

# 终止线程

```c++
#include <iostream>
#include <thread>
#include <pthread.h>

using namespace std;

void f() {
    std::this_thread::sleep_for(std::chrono::seconds(2));
    cout << "f(): mid check" << endl;
    std::this_thread::sleep_for(std::chrono::seconds(2));
    cout << "f(): done" << endl;
}

void fl() {
    while (true) {}
    cout << "fl() done" << endl;
}

int main() {
    std::thread th(f);
    thread::native_handle_type handle = th.native_handle();
    th.detach();
    std::this_thread::sleep_for(std::chrono::seconds(3));
    pthread_cancel(handle);

    std::thread thfl(fl);
    thfl.detach();
    handle = thfl.native_handle();
    cout << "Handle: " << handle << endl;
    pthread_cancel(handle);
    std::this_thread::sleep_for(std::chrono::seconds(3));

    return 0;
}
/** Output
f(): mid check
Handle: 0
*/
```

- 向线程发送终止信号，并不能立即终止线程运行，只有在线程运行到检查点时，才会终止。

# std::this_thread

- `get_id()`

  - `std::this_thread::get_id()`

- `sleep_for()`

  - `std::this_thread::sleep_for(std::chrono::seconds(1))`
  - 线程休眠某个指定的时间片

- `yield()`

  - `std::this_thread::yield`
  - 放弃当前线程执行

- `sleep_until()`

  ```c++
  template< class Clock, class Duration >
  void sleep_until( const std::chrono::time_point<Clock,Duration>& sleep_time );
  ```

  - 线程休眠至某个指定的时刻，该线程才被重新唤醒

- `sleep_for`

# Examples

## 主线程早于子线程退出

```c++
#include <iostream>
#include <thread>
#include <unistd.h>
#include <fstream>

using namespace std;

void f() {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    cout << "f() finish" << endl;
    ofstream out("out", ofstream::out | ofstream::trunc);
    if (out.is_open()) {
        out << "HELLO";
    }
    out.close();
    return;
}

int main() {
    std::thread ths[5];
    ths[0] = std::thread();
    cout << ths[0].joinable() << endl;  // 0
    // ths[0].join();                      // terminate called after throwing an instance of 'std::system_error'; Invalid argument
    ths[1] = std::thread(f);

    ths[1].detach();
    cout << "main() finish" << endl;
    return 0;
}

/** Output
0
main() finish
*/
// 没有打印日志和生成文件

/** 将ths[1].detach()改为ths[1].join()后，Output:
0
f() finish
main() finish
*/
// 有生成文件
```

- 子线程会被迫终止

## 线程作为方法参数

```c++
#include <iostream>
#include <thread>

using namespace std;

void fb() {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    cout << "fb() done" << endl;
}

void f(thread &thd) {
    cout << thd.get_id() << endl;
    thd.join();
}

void fc(thread thd) {
    cout << thd.get_id() << endl;
    thd.join();
}

int main() {
    thread th1(fb);
    thread th2(fb);
    cout << th1.joinable() << endl;
    cout << th2.joinable() << endl;
    f(th1);		
    // fc(th1);	// ERROR: 由于thread禁用了赋值操作符，这里编译时会报错
    			// 	      这里的th1是不能拷贝复制给形参的
    fc(std::move(th2));
    cout << th1.joinable() << endl;
    cout << th2.joinable() << endl;
    return 0;
}
/** Output
1
1
140432286369536
fb() done
fb() done
140432277976832
0
0
*/

// 一个值得注意的地方，如果main函数改为:
/**
int main() {
    thread th1(fb);
    f(th1);					// 更改了这句的顺序
    thread th2(fb);
    cout << th1.joinable() << endl;
    cout << th2.joinable() << endl;
    fc(std::move(th2));
    cout << th1.joinable() << endl;
    cout << th2.joinable() << endl;
    return 0;
}
*/
// 那么输出为
/**
140007787820800
fb() done
0
1
140007787820800
fb() done
0
0
*/
// 有意思的是, 这里的线程ID相同, 有时间再细究, 编译器的优化?
```

## 判断线程是否有效

- 可以通过`joinable`方法判断线程对象有没有被初始化

# PS

- `std::thread::native_handle()` 函数只有在调用`join()`和`detach()`之前有效，之后调用会返回 0，如果此时再调用 `pthread_cancel()` 传入返回值，试图这样来结束改进程（由于返回值是0，并不能结束该线程），会产生core dump

  ```c++
  #include <iostream>
  #include <thread>
  #include <pthread.h>
  
  using namespace std;
  
  void f() {
      std::this_thread::sleep_for(std::chrono::seconds(3));
  }
  
  int main() {
      std::thread th(f);
      thread::native_handle_type handle = th.native_handle();
      cout << "ID: " << handle << endl;
      cout << "Handle: " << th.get_id() << endl;
      th.join();
      cout << "Handle: " << th.native_handle() << endl;
      return 0;
  }
  /** Output
  ID: 139748843316992
  Handle: 139748843316992
  Handle: 0
  */
  ```

## 线程+lambda

```shell
#### example 1
$ cat 07.cpp 
#include <iostream>
#include <unistd.h>
#include <thread>

using namespace std;

int main() {
    thread th([]() {
        int i = 0;
        while (true) {
            cout << "- " << i++ << " -" << endl;
            sleep(2);
        }
    });
    th.join();
    return 0;
}
$ g++ 07.cpp -lpthread && ./a.out 
- 0 -
- 1 -

#### example 2
$ cat 13.cpp
#include <iostream>
#include <future>
#include <thread>
#include <chrono>

using namespace std;

int main() {
    std::promise<bool> p;
    thread th([&]() {
        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
        cout << "set value true" << endl;
        p.set_value(true);
    });
    th.detach();
    cout << "th detach" << endl;
    p.get_future().wait();
    cout << "exit" << endl;
    return 0;
}
$ g++ 13.cpp -lpthread -std=c++11 && ./a.out
th detach
set value true
exit
```

# 参考

- [线程管理](http://www.cplusplus.com/forum/beginner/196573/)
- [cplusplus - thread](http://www.cplusplus.com/reference/thread/thread/)

# 编译

```shell
g++ -std=c++11 -pthread 002.cpp	# g++-4.8
```