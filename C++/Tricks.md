---
title: C++ Tricks
tags:
	- C++
	- Tricks
categories:
	- C++
---

# Shuffle

```c++
static void shuffle(vector<int>* v) {
  srand(9);
  for (int n = v->size(); n >= 2; n--) {
    swap((*v)[n - 1], (*v)[static_cast<unsigned>(rand()) % n]);
  }
}
```

