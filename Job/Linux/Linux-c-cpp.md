# fork

- 一个进程，包括代码、数据和分配给进程的资源。fork（）函数通过系统调用创建一个与原来进程几乎完全相同的进程，也就是两个进程可以做完全相同的事，但如果初始参数或者传入的变量不同，两个进程也可以做不同的事。
- 一个进程调用fork（）函数后，系统先给新的进程分配资源，例如存储数据和代码的空间。然后把原来的进程的所有值都复制到新的新进程中，只有少数值与原来的进程的值不同。相当于克隆了一个自己。

## 示例

```c
//// 1
#include <stdio.h>
int main()
{
    int i;
    for (i = 0; i < 3; i++)
    {
        fork();
        printf("*\n");
    }
    return 0;
}
// 输出: 14个 *

//// 2
#include <stdio.h>
int main()
{
    int i;
    for (i = 0; i < 3; i++)
    {
        fork();
        printf("*\n");
    }
    return 0;
}
// 输出: 14个*

//// 3
#include <stdio.h>

int main()
{
    int i;
    for (i = 0; i < 3; ++i)
    {
        printf("*\n");
        fork();
    }
    return 0;
}
// 输出: 7个*
```

## 参考

- [参考一](http://blog.csdn.net/myarrow/article/details/8995091)
- [参考二](https://www.nowcoder.com/questionTerminal/553c29f704434152b2e2ebacb979a211)



# 文件

## 读写

Linux下对文件操作有两种方式：

- 系统调用(system call)

- 库函数调用(Library  functions)


系统调用实际上就是指最底层的一个调用，在linux程序设计里面就是底层调用的意思。面向的是硬件。而库函数调用则面向的是应用开发的，相当于应用程序的api。 

函数库调用是语言或应用程序的一部分，而系统调用是操作系统的一部分。

## 文件系统函数

| 函数       | 描述              |
| -------- | --------------- |
| fcntl    | 文件控制            |
| open     | 打开文件            |
| creat    | 创建新文件           |
| close    | 关闭文件描述字         |
| read     | 读文件             |
| write    | 写文件             |
| readv    | 从文件读入数据到缓冲数组中   |
| writev   | 将缓冲数组里的数据写入文件   |
| pread    | 对文件随机读          |
| pwrite   | 对文件随机写          |
| lseek    | 移动文件指针          |
| _llseek  | 在64位地址空间里移动文件指针 |
| dup      | 复制已打开的文件描述字     |
| dup2     | 按指定条件复制文件描述字    |
| flock    | 文件加/解锁          |
| poll     | I/O多路转换         |
| truncate | 截断文件            |
| umask    | 设置文件权限掩码        |
| fsync    | 把文件在内存中的部分写回磁盘  |

## 文件库函数

| 函数      | 描述           |
| ------- | ------------ |
| fwrite  | 将数据写入文件流     |
| putc    | 将一个字符写入文件流中  |
| putchar | 向终端输出一个字符    |
| getline | 从输入流中读入一个字符串 |
| scanf   | 格式输入函数       |





# END