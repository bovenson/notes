---
title: STL 容器
tags:
	- 容器
categories:
	- C++
---

# 序列式容器

所谓序列式容器：

- 元素排列有顺序可寻(ordered)
- 未必有序(sorted)

STL中序列式容器包括：

- vector

## vector

- 和array相比：
  - array是静态的，大小固定（元素可变）
  - vector是动态的，长度大小可变

- 迭代器

  - 提供的是 Random Access Iterators

- 数据结构

  - 线性连续空间
  - 运用start、finish、end_of_storage三个迭代器，可轻松提供：
    - 首尾标识
    - 大小
    - 容量
    - 空容器判断
    - 注标（[]）运算子
    - ...

- 构造函数

  ```c++
  // empty container constructor
  explicit vector (const allocator_type& alloc = allocator_type());
  
  // fill constructor
  // 指定size, 并调用指定类型默认构造函数构造对象并填充
  explicit vector (size_type n);
  explicit vector (size_type n, const value_type& val = value_type(), const allocator_type& alloc = allocator_type());
  
  // range constructor
  template <class InputIterator> vector (InputIterator first, InputIterator last, const allocator_type& alloc = allocator_type());
  
  // copy constructor
  vector (const vector& x);
  vector (const vector& x, const allocator_type& alloc);	
  
  // c++11
  // move constuctor
  vector (vector&& x);
  vector (vector&& x, const allocator_type& alloc);
  
  // initializer list
  // Constructs a container with a copy of each of the elements in il, in the same order
  vector (initializer_list<value_type> il,
         const allocator_type& alloc = allocator_type());
  ```

- Iterators

  - begin
  - end
  - rbegin
    - Return reverse iterator to reverse beginning
    - 返回反向迭代器的开始
  - rend
    - Return reverse iterator to reverse end
  - cbegin
    - c++11
    - const pointer
  - cend
    - c++11
    - const pointer
  - crbegin
    - c++11
    - const pointer
  - crend
    - c++11
    - const pointer

- Capacity

  - size
  - max_size
  - resize
  - capacity
  - empty
  - reserve
  - shrink_to_fit
    - c++11

- Element access

  - operator[]
  - at
  - front
  - back
  - data
    - c++11

- Modifiers

  - assign
  - push_back
  - pop_back
  - insert
  - erase
  - swap
  - clear
  - emplace
    - c++11
    - Construct and insert element
  - emplace_back
    - c++11

## list

- 数据结构

  - **环状**双向链表

- 迭代器

  - Bidirectional Iterators
  - 插入操作和接合操作不会造成原有list迭代器失效

- Constructor

  ```c++
  // empty container constructor
  explicit list (const allocator_type& alloc = allocator_type());
  
  // fill constructor
  explicit list (size_type n, const value_type& val = value_type(),
                  const allocator_type& alloc = allocator_type());
  
  // range constructor
  template <class InputIterator>
    list (InputIterator first, InputIterator last,
           const allocator_type& alloc = allocator_type());
  
  // copy constructor
  // Constructs a container with a copy of each of the elements in x, in the same order.
  list (const list& x);
  
  // c++11
  // move constructor
  /* Constructs a container that acquires the elements of x.
  If alloc is specified and is different from x's allocator, the elements are moved. Otherwise, no elements are constructed (their ownership is directly transferred).
  x is left in an unspecified but valid state. */
      
  list (list&& x);
  list (list&& x, const allocator_type& alloc);
  
  // initializer list
  list (initializer_list<value_type> il,
         const allocator_type& alloc = allocator_type());
  ```

- Iterators

  - begin
  - end
  - rbegin
  - rend
  - c++11
    - cbegin
    - cend
    - crbegin
    - crend

- Capacity

  - empty
  - size
  - max_size

- Element access
  - front
  - back
- Modifiers
  - assign
  - emplace_front
    - c++11
  - push_front
  - pop_front
  - emplace_back
    - c++11
  - push_back
  - pop_back
  - emplace
    - c++11
  - insert
  - erase
  - swap
  - resize
  - clear

- Element access
  - splice
  - remove
  - remove_if
  - unique
  - merge
  - sort
  - reverse

## priority_queue

- empty
- size
- top
- push
- emplace
  - c++11
- pop
- swap
  - c++11

# 关联式容器

