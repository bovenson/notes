---
Title: STL Practice
---

# vector

## Initialization

```c++
// create empty vector 
vector<int> vect; 

// create a vector with n size & all value is 10
vector<int> vect(n, 10); 

// initialization list
vector<int> vect{ 10, 20, 30 }; 

// use list
int arr[] = { 10, 20, 30 }; 
int n = sizeof(arr) / sizeof(arr[0]); 
vector<int> vect(arr, arr + n); 

vector<int> vect1{ 10, 20, 30 }; 
vector<int> vect2(vect1.begin(), vect1.end()); 
```

# 通用

## 容器的遍历

```c++
$ cat 08.cpp 
#include <iostream>
#include <vector>

using namespace std;

int main() {
    
   	// positive traversal 
    vector<int> v{1, 2, 3, 4};
    for (auto i : v) {
        cout << i << endl;
    }

    cout << "----" << endl;

    for (auto iter=v.begin(); iter != v.end(); ++iter) {
        cout << *iter << endl;
    }
    
    cout << "----" << endl;

    // reverse traversal
    for (auto iter=v.rbegin(); iter != v.rend(); ++iter) {
        cout << *iter << endl;
    }
    return 0;
}
$ g++ 08.cpp -std=c++11 && ./a.out 
1
2
3
4
----
1
2
3
4
----
4
3
2
1
```

## swap elements

```c++
// swap based on the objects contents:
std::swap(v[0],v[1]);

// swap based on the underlying iterator:
std::iter_swap(v.begin(),v.begin()+1);
```

- [参考](https://stackoverflow.com/questions/6224830/c-trying-to-swap-values-in-a-vector)