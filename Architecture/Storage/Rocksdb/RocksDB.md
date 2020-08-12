[toc]

# Description

RocksDB是一个应用于快速存储环境的持久化kv存储。最初在Facebook作为存储引擎来处理各种存储介质上的服务器工作负载。RocksDB是一个c++库，用于存储键值对，两者都是任意大小的字节流，支持点查询和范围扫描，提供不同类型的ACID保证。

RocksDB具有高度灵活的配置设置，可以被调整为适合多种生产环境，包括纯内存、闪存、硬盘、远程存储，支持多种压缩算法。

RocksDB借鉴了leveldb的核心代码以及Apache HBase的一些构想。

# Feature

## **高性能**

使用完全由C++编写的结构化日志数据库引擎，键值对是任意大小的字节流。可通过配置支持高随机读、高写入/更新、或者两者兼有的场景。RocksDB的架构支持方便地调整针对不同硬件和工作负载的平衡方案。

## **针对快速存储优化**

针对快速、低延迟存储优化。

## **适用性强**

可用于多种数据需求。

## **支持基础、高级数据库选项**

## **列族**

RocksDB支持将数据库实例划分为多个列族，如果没有指定列族，将使用默认的列族名字`default`。

## 操作

### 更新

**Put**

向数据库中插入单个k-v，如果key已经存在，旧的数据会被重写。

**Write**

允许多个k-v自动地插入、更新、删除，RocksDB保证一次Write操作中的所有的k-v要么全被插入，要么没有一个被插入数据库中，如果key存在，数据会被覆盖。

**Range Delete**

用于所有指定范围的所有key。RocksDB支持设定key前缀进行扫描。

### 查询

键和值被是没有大小限制的字节流。

**Get**

查询单个key。

**MultiGet**

允许应用程序从数据库中检索一堆key。

### 遍历

数据库中所有数据均按排序顺序进行逻辑排序，应用程序可以指定key的排序方法，指定key的排序方式。

**Iterator**

Iterator允许应用程序对数据库做RangeScan操作，迭代器可以查询指定key，应用程序可以从改点一次扫描一个key。Iterator也可用于反向遍历。创建Iterator时，将创建数据库的一致时间点视图。因此，通过Iterator返回的所有键都是从数据库的一致视图中获得的。

**快照**

Snapshot API允许应用程序创建数据库的时间点视图。Get和Iterator API可用于从指定的快照读取数据。从某种意义上说，快照和迭代器都提供了数据库的时间点视图，但是它们的实现是不同的。短期/向前扫描最好通过迭代器完成，而长时间运行/向后扫描最好通过快照完成。迭代器会在与数据库的该时间点相对应的所有基础文件上保留引用计数-在释放迭代器之前，不会删除这些文件。快照并不能防止文件删除，压缩进程知道快照的存在，并承诺不会删除任何现有快照中可见的密钥。

快照在数据库重新启动后不会持久保存：重新加载RocksDB库（通过服务器重新启动）会释放所有先前存在的快照。

## 持久化

RocksDB支持预写日志（Write Ahead Log，WAL）。所有Put都存储在称为memtable的内存缓冲区中，也可以选择插入WAL中。重新启动后，它将重新处理日志中记录的所有事务。

可以将WAL配置为存储在与存储SST文件的目录不同的目录中。同时，通过将所有事务日志放在较慢但持久的存储中，以确保数据不会丢失。

每个Put都有一个通过WriteOptions设置的标志，配置是否将Put插入事务日志中。WriteOptions还可以指定在声明要提交Put之前是否向事务日志发出同步调用。

在内部，RocksDB使用批处理提交机制将事务批处理到日志中，以便它有可能使用单个同步调用来提交多个事务。

## 数据校验

RocksDB使用校验和来检测存储中的数据是否损坏。这些校验和用于每个SST文件块（大小通常在4K到128K之间）。文件块一旦写入存储，就永远不会修改。RocksDB动态检测对校验和计算的硬件支持，并在可用时利用该支持。

## Compactions

### 多线程Compactions

Compactions操作可以移除相同key的多个副本，也用于删除key。如果配置适当，compaction操作可以在多个线程中执行。

**存储结构**

整个数据库被存储在一系列的sstfiles中。当一个memtable被填满之后，会被写到LSM数Level-0(L0)层中的文件。当写入文件中时，RocksDB会移除重复的key和被重写的key。

在Compaction操作时，部分文件会被定期读取，并被合并成更大的文件，通常进入下一个LSM级别。

LSM数据库的总写吞吐直接取决于Compactions的速度，尤其是当数据存储在SSD或者RAM等快速存储中时。RocksDB可以配置多个线程执行Compactions。与单线程压缩相比，当数据库位于SSD上时，使用多线程压缩持续写入率可能会增加多达10倍。

### Compaction Styles

Level Style Compaction和Universal Style Compaction都将数据存储在固定数量的逻辑levels中。通常最新的数据保存在Level-0，老的数据在更高的levels中，知道Level-max。L0中的文件可能存在重叠的键，但是其他level的文件通常在每个级别都是唯一且排序的。

**Level Style Compaction** 通常通过最小化每个压缩步骤中设计的文件来优化磁盘占用量与逻辑数据库大小：将Lb中的一个文件与Ln+1中的所有重叠文件合并，并用新文件替换Ln+1中的文件。[空间放大]

**Universal Style Compation** 通常通过一次合并潜在的多个文件和Level来优化写入磁盘的总字节数与逻辑数据库的大小。[写放大]

**FIFO Style Compactions**时丢弃最旧的文件，并可用于类似缓存的数据。

**Custom Compaction Policies** RocksDB具有适当的钩子来关闭内置的Compaction算法，来允许应用程序自定义的Compaction算法。

### Metadata Storage

`MANIFEST`文件记录数据库状态，Compaction进程会添加新文件并删除数据库中存在的文件，并通过将这些操作记录在MANIFEST文件中来使这些操作持久化。

### 避免阻塞

后台Compaction线程还用于将可存储内容刷新到存储中的文件。如果所有后台Compaction线程都在长时间运行Compaction，那么突发写入会迅速将memtable填满，从而使新的写入停止。这种情况可以通过配置RocksDB来指定一小组保留线程负责将memtable数据写入存储空间来解决。

### Compaction Filter

有些应用想要在Compaction时处理key，比如支持TTL的数据库想要移除过期的key，可以通过应用程序自定义Compaction Filter来实现。如果应用程序要连续删除早于特定时间的数据，则可以使用Compaction Filter删除已过期的记录。RocksDB Compaction Filter将控制权交给应用程序，以在Compaction过程中修改value或删除key-value。例如，作为Compaction的一部分，应用程序可以连续运行数据清理器。

## 只读模式

可以以只读模式打开数据库，在该模式下数据库保证应用程序不会修改数据库中的任何内容。经常被便利的代码路径完全避免锁定，带来更高的数据读性能。

## Debug Logs

RocksDB默认将详细日志写入名为LOG*的文件中，通常用于调试和分析正在运行的系统。用户可以选择不同的日志级别，也可配置日志以指定的周期滚动，日志记录接口是可插入的，用户可以插入其他logger。

## 数据压缩

RocksDB支持lz4、zstd、snappy、zlib和lz4_nc压缩，Windows下还支持xpress。RocksDB支持为最底层的数据配置不同的压缩算法，通常最底层配置zstd算法，其他层配置lz4。

## 完整备份和副本(Replication)

RocksDB提供备份引擎BackupableDB。

RocksDB本身不是可复制的，但是可以提供一些辅助函数来使得用户在RocksDB上层实现副本(replication)系统。

## 同一个进程支持多嵌入数据库

RocksDB的一个常见用例是应用程序将其数据集划分为逻辑分区或分片，这有利于应用程序负载平衡和从故障中快速恢复。这意味着单个服务器进程应该能够同时操作多个RocksDB数据库，这可以通过Env环境变量完成，一个线程池和Env绑定，如果应用程序希望在多个数据库实例之间共享一个公共线程池（用于后台Compactions），那么他们应该使用相同的Env对象打开那些数据库。

## 块缓存(Block Cache)

RocksDB使用LRU缓存来服务读操作。类似的，多个数据库实例可以共享相同的块缓存。块缓存分为两个单独的缓存：第一个缓存未压缩的块，第二个缓存RAM中的压缩块。

如果配置了压缩块缓存，则用户可能希望启用直接I / O，以防止OS页面缓存对相同的压缩数据进行双重缓存。

## 表缓存

表缓存是一种缓存打开文件描述符的结构。这些文件描述符用于sstfiles。应用程序可以指定表缓存的最大大小，或将RocksDB配置为始终保持所有文件打开，以实现更好的性能。

## I/O控制

RocksDB允许用户以不同的方式配置SST文件之间的I/O。用户可以启用直接I/O，以便RocksDB可以完全控制I/O和缓存。一种替代方法是利用某些选项，以允许用户提示应如何执行I / O。他们可以建议RocksDB在文件中调用fadvise以进行读取，在要附加的文件中调用周期性范围同步，或启用直接I / O。

## Stackable DB

RocksDB有一个内置的包装器机制，可将功能添加为代码数据库内核之上的一层。此功能由StackableDB API封装。例如，生存时间功能由StackableDB实现，而不是核心RocksDB API的一部分。这种方法使代码保持模块化和整洁。

## Memtables

### Pluggable Memtables

RocksDB的内存表的默认实现是一个跳过列表。跳过列表是一个有序集，当工作负载交错进行范围扫描写入时，这是一个必需的构造。但是，某些应用程序不交错写入和扫描，而某些应用程序根本不进行范围扫描。对于这些应用程序，排序集可能无法提供最佳性能。因此，RocksDB的内存表是可插入的。提供了一些替代实现。库中包含三个内存表：一个跳过列表内存表，一个矢量内存表和一个前缀哈希内存表。向量存储器表适合于将数据批量加载到数据库中。每次写操作都会在向量的末尾插入一个新元素；当需要刷新存储表以存储向量中的元素时，将其排序并写到L0中的文件中。前缀哈希表可以有效地处理键前缀内的获取，放置和扫描。尽管未将memtable的可插入性作为公共API提供，但应用程序有可能在私有fork中提供其自己的memtable实现。

### Memtable Pipelining

RocksDB支持为数据库配置任意数量的内存表。当内存表已满时，它将变为不可变的内存表，并且后台线程开始将其内容刷新到存储中。同时，新的写入继续累积到新分配的内存表中。如果新分配的内存表已满，将其转换为不可变内存表，并将其插入刷新管道中。后台线程继续将所有流水线不可变的内存表刷新到存储中。这种流水线可以提高RocksDB的写入吞吐量，尤其是在慢速存储设备上运行时。

### Garbage Collection

当将内存表刷新到存储时，将执行内联压缩过程。垃圾的清除方式与压实相同。从输出流中删除了同一密钥的重复更新。同样，如果先前的放置权被后面的删除隐藏，则该放置权根本不会写入输出文件。对于某些工作负载，此功能可大大减少存储数据的大小并扩大写入放大。

### Merge Operator

RocksDB本机支持三种类型的记录，即放置记录，删除记录和合并记录。当压缩过程遇到合并记录时，它将调用一个应用程序指定的方法，称为合并运算符。合并可以将多个Put和Merge记录合并为一个记录。这项强大的功能允许通常执行读取-修改-写入的应用程序完全避免读取。它允许应用程序将操作意图记录为“合并记录”，并且RocksDB压缩过程将该意图懒惰地应用于原始值。

### DB ID

在创建数据库时创建的全局唯一ID，默认情况下存储在DB文件夹的IDENTITY文件中。（可选）只能将其存储在MANIFEST文件中。建议存储在MANIFEST文件中。

# High Level Architecture

RocksDB是kv存储接口的底层存储引擎库，对应的kv都是任意大小的字节流。RocksDB以有序的方式组织所有数据，通用操作有`Get(key)`，`NewIterator()`，`Put(key, val)`，`Delete(key)`，`SingleDelete(key)`。

RocksDB三个基本组件是 memtable、sstfile、logfile。memtable是内存中的数据结构，新写入的数据被塞入memtable，也可以选择同时写入logfile。logfile是存储中基于序列化写入的文件。memtable填满后，将刷新到存储中的sstfile中，对应的logfile也会被安全地删除。sstfile中的数据是经过排序的，以方便快速查找key。