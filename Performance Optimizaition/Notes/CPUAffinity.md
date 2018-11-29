# 位掩码

在 Linux 内核中,所有的进程都有一个相关的数据结构,称为 task_struct。这个结构非常重要,原因有很多;其中与 亲和性(affinity)相关度最高的是 cpus_allowed 位掩码。这个位掩码由 *n* 位组成,与系统中的 *n* 个逻辑处理器一一对应。 具有 4 个物理 CPU 的系统可以有 4 位。如果这些 CPU 都启用了超线程,那么这个系统就有一个 8 位的位掩码。

如果为给定的进程设置了给定的位,那么这个进程就可以在相关的 CPU 上运行。因此,如果一个进程可以在任何 CPU 上运行,并且能够根据需要在处理器之间进行迁移,那么位掩码就全是 1。实际上,这就是 Linux 中进程的缺省状态。

调用 sched_setaffinity 会设置由 pid 所引用的进程的 CPU 亲和性(affinity)掩码。

# 参考

- [Ref 1](https://www.cnblogs.com/LubinLew/p/cpu_affinity.html)
- [Ref 2](https://www.cnblogs.com/wenqiang/p/6049978.html)

