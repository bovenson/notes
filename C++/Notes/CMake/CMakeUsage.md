# 判断Mac OS

```cmake
if (APPLE)
  # do something
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}")
elseif (UNIX)
  # do something
  set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -pthread")
endif()
```

