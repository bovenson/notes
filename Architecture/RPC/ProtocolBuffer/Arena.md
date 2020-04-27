# Arena

仅适用于C++，帮助优化内存使用。

# 为什么使用Arena

- 在Protocol Buffers代码中，内存分配 / 回收占用大量时间。
- 默认情况下，Protobuf 使用堆为每个Message、它的子对象、集中字段类型分配内存。在构造消息和解析消息时，这中内存分配会大量发生；当子对象树被释放时，也会发生相应的内存回收操作。
- 使用Arena，新的对象会从预分配的Arena空间中获取内存。
- 对象可以通过抛弃整个Arena区域一次释放。这种方式，在分配内存时更快，消耗减少到一个简单的指针增加操作；同时，内存释放几乎不耗费时间。
- 使用Arena可以获得更好的缓存命中率：当消息被解析时，它们更有可能在连续内存中分配，当遍历消息时，更有可能命中Cache。

# Getting stared

**enable arena**

在protobuf定义文件中，添加：

`option cc_enable_arenas = true;`

来生成额外的代码支持Arena，用来为Message分配内存，Example：

```c++
#include <google/protobuf/arena.h>
{
  google::protobuf::Arena arena;
  MyMessage* message = google::protobuf::Arena::CreateMessage<MyMessage>(&arena);
  // ...
}
```

message对象的存活时间和arena一样长，而且，不应该手动`delete`掉返回的message 指针。message对象及子消息都会被分配在arena区域中。

# Arena class API

## Constructors

- `Arena()`
  - 创建一个新的arena，使用默认参数
- `Arena(const ArenaOptions& options)`

## Allocation methods

- `template<typename T> static T* CreateMessage(Arena *arena)` 

  - 在Arena中创建一个新的 protobuf 消息对象
  - 如果 arena 不为 `NULL`，则返回的消息对象在该 arena 中分配，它的内部存储、子消息会同样被分配在该arena，它们的生命周期和该arena一样。该对象禁止手动 delete/free。
  - 如果 arena 为 `NULL`，则返回的消息分配在堆上，调用者拥有该对象。

- `template<typename T> static T* Create(Arena* arena, args...)`

  - 和`CreateMessage()`类似，但是允许在arena中创建任意class类型的对象，比如：

    ```c++
    // C++ 类
    class MyCustomClass {
        MyCustomClass(int arg1, int arg2);
        // ...
    };
    
    // 创建实例
    void func() {
        // ...
        google::protobuf::Arena arena;
        MyCustomClass* c = google::protobuf::Arena::Create<MyCustomClass>(&arena, constructor_arg1, constructor_arg2);
        // ...
    }
    ```

- `template<typename T> static T* CreateArray(Arena* arena, size_t n)`

  - 如果 `arena` 是 `NULL` ，该方法分配n个T类型对象的raw storage，并返回。arena拥有返回的内存，在arena销毁时释放该内存。
  - 如果 `arena` 是 `NULL`，该方法在堆上分配内存，调用者拥有该段内存。
  - T 必须有一个简单构造函数，在arena上构造array时，不会调用构造函数。

## Owned list methods

下面的方法运行指定特定的对象或析构函数 '属于' 一个arena，确保在arena被删除时，这些对象也会被释放。

- `template<typename T> void Own(T* object)`
  - 添加object至arena拥有的堆对象列表
  - 当arena销毁时，会遍历该列表使用 delete 关键词释放列表中每个对象。
- `template<typename T> void OwnDestructor(T* object)`
  - 添加对象的析构函数至arena的析构函数列表
  - arena销毁时，会遍历执行该列表中的析构函数

# Generated message class

