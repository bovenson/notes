# 排序

```c++
# 1
template <class RandomAccessIterator> 
  void sort (RandomAccessIterator first, RandomAccessIterator last);

# 2
template <class RandomAccessIterator, class Compare>
  void sort (RandomAccessIterator first, RandomAccessIterator last, Compare comp);
```

## **数组排序**

**std::sort**

```c++
#include <algorithm>
#include <functional>
#include <array>
#include <iostream>
 
int main()
{
    std::array<int, 10> s = {5, 7, 4, 2, 8, 6, 1, 9, 0, 3}; 
 
    // 用默认的 operator< 排序
    std::sort(s.begin(), s.end());
    for (auto a : s) {
        std::cout << a << " ";
    }   
    std::cout << '\n';
 
    // 用标准库比较函数对象排序
    std::sort(s.begin(), s.end(), std::greater<int>());
    for (auto a : s) {
        std::cout << a << " ";
    }   
    std::cout << '\n';
 
    // 用自定义函数对象排序
    struct {
        bool operator()(int a, int b) const
        {   
            return a < b;
        }   
    } customLess;
    std::sort(s.begin(), s.end(), customLess);
    for (auto a : s) {
        std::cout << a << " ";
    }   
    std::cout << '\n';
 
    // 用 lambda 表达式排序
    std::sort(s.begin(), s.end(), [](int a, int b) {
        return b < a;   
    });
    for (auto a : s) {
        std::cout << a << " ";
    } 
    std::cout << '\n';
}
```

**qsort**

```c++
#include <iostream>
#include <stdlib.h>

using namespace std;

void display(int arr[], int n) {
    for (int i=0; i < n; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

int cmpa(const void *a, const void *b) {
    return *((int*)a) - *((int*)b);
}

int cmpb(const void *a, const void *b) {
    return *((int*)b) - *((int*)a);
}

int main() {
    int arri[8] = {1, 4, 2, 3, 9, 0, -1, 2};
    qsort(arri, 8, sizeof(int), cmpa);
    display(arri, 8);

    int arrj[8] = {1, 4, 2, 3, 9, 0, -1, 2};
    qsort(arrj, 8, sizeof(int), cmpb);
    display(arrj, 8);
    return 0;
}

/** Output
-1 0 1 2 2 3 4 9 
9 4 3 2 2 1 0 -1
*/
```

## 二维数组排序

```c++
#include <iostream>

using namespace std;

int cmp(const void *pa, const void *pb) {
    const int *pc = (const int *)pa;
    const int *pd = (const int *)pb;
    return pc[0] - pd[0];
}

int main() {
    int n;
    cin >> n;
    int arr[n][2];
    for (int i = 0; i < n; ++i) {
        cin >> arr[i][0] >> arr[i][1];
    }
    cout << sizeof(arr[0]) << endl;
    qsort(arr, n, sizeof(arr[0]), cmp);
    for (int i = 0; i < n; ++i) {
        cout << arr[i][0] << " " << arr[i][1] << endl;
    }
    return 0;
}

/** Input
3  
1 1
2 2
0 0

// Output
8
0 0
1 1
2 2
*/
```

## 结构体排序

```c++
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

struct A {
public:
    int a, b;
};

int cmp(const void *pa, const void *pb) {
    return ((A*)pa)->a - ((A*)pb)->a;
}

int cmpb(const void *pa, const void *pb) {
    return ((A*)pa)->b - ((A*)pb)->b;
}

int main() {
    int cnt = 5;
    A a[cnt];
    for (int i = 0; i < cnt; i++) {
        a[i].a = i;
        a[i].b = cnt -i;
        cout << a[i].a << " " << a[i].b << endl;
    }
    cout << endl;
    qsort(a, cnt, sizeof(a[0]), cmp);
    for (int i = 0; i < cnt; i++) {
        cout << a[i].a << " " << a[i].b << endl;
    }
    
    cout << endl;
    qsort(a, cnt, sizeof(a[0]), cmpb);
    for (int i = 0; i < cnt; i++) {
        cout << a[i].a << " " << a[i].b << endl;
    }
    return 0;
}

/** Output
0 5
1 4
2 3
3 2
4 1

0 5
1 4
2 3
3 2
4 1

4 1
3 2
2 3
1 4
0 5
*/
```

# 数据结构初始化

## map

```c++
// Initializer list
std::map <int, std::string> x { 
  { 42, "foo" }, 
  { 3, "bar" } 
};
```

