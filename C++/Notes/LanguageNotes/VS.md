---
title: C++笔记
tags:
	- 笔记
categories:
	- C++
---

# int & new int()

```c++
#include <iostream>

using namespace std;

int main() {
    int *a = new int(1);
    cout<<a<<endl<<*a<<endl;
    while (true) {
        new int(1);
    }
}

// 上面这段代码有内存泄露的问题
// 下面的不会

#include <iostream>

using namespace std;

int main() {
    int *a = new int(1);
    cout<<a<<endl<<*a<<endl;
    while (true) {
        // new int(1);
        int b = 1;
    }
}
```

# new/delete & malloc/free

**使用**

```c++
//// malloc
// 原型
void * malloc(size_t size);
// 使用
int *p = (int *) malloc(sizeof(int) * length);

//// new
int *array = new int[5];

//// free
free(p);	// 不用指定长度

//// delete
delete[] array
delete p
```



|   项目   |                     new/delete                     |         malloc/free          |
| :------: | :------------------------------------------------: | :--------------------------: |
|   属性   |        **C++关键字/操作符**，需要编译器支持        |  **库函数**，需要头文件支持  |
|   参数   | 无须指定内存块的大小，编译器会根据类型信息自行计算 | 需要显式地指出所需内存的尺寸 |
| 返回类型 |            返回对象类型的指针，无需转换            |   返回void*指针，需要强转    |
| 分配失败 |                 抛出bas_alloc异常                  |           返回NULL           |

**其他**

- new运算符内部数据类型不进行初始化，会自动调用非内部数据类型的默认构造函数
- 由于 malloc/free 是库函数而不是运算符，不在编译器控制权限之内，不能够把执行构造函数和析构函数的任务强加malloc/free