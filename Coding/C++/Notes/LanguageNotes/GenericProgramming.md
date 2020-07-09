# 模板函数

```c++
// define
template <class T>
std::vector<T> readVec(const char *f) {
    std::vector<T> res;
    ifstream in(f);
    T number;
    while(in >> number) {
        res.push_back(number);
    }
    return res;
}

// use
vector<string> inp = readVec<string>("in");
```

