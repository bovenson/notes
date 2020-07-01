---
titile: Mutex & Lock
---

# Mutex

```c++
std::mutex m_;
```

# Lock

```c++
// std::mutex m_; 		// define
std::unique_lock<std::mutex> lock(m_);
```

