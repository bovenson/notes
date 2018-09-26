# Sort

```c++
# 1
template <class RandomAccessIterator> 
  void sort (RandomAccessIterator first, RandomAccessIterator last);

# 2
template <class RandomAccessIterator, class Compare>
  void sort (RandomAccessIterator first, RandomAccessIterator last, Compare comp);
```

## **数组排序**

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

