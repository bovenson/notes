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
  - 