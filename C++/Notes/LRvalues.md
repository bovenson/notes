---
title: 右值引用
tags:
	- C++
categories:
	- C++
---

# &&

利用右值引用，可以将左值与右值区分开。

右值引用支持移动语义的实现，可以显著提高应用程序的性能。 利用移动语义，您可以编写将资源（如动态分配的内存）从一个对象转移到另一个对象的代码。 移动语义很有效，因为它使资源能够从无法在程序中的其他位置引用的临时对象转移。

若要实现移动语义，您通常可以向您的类提供移动构造函数，也可以提供移动赋值运算符 (`operator=`)。 其源是右值的复制和赋值操作随后会自动利用移动语义。 与默认复制构造函数不同，编译器不提供默认移动构造函数。

还可以重载普通函数和运算符以利用移动语义。例如，`string` 类实现了执行移动语义的操作。

```c++
// string_concatenation.cpp  
// compile with: /EHsc  
#include <iostream>  
#include <string>  
using namespace std;  
  
int main()  
{  
   string s = string("h") + "e" + "ll" + "o";  
   cout << s << endl;  
}  
```

之前，每个对 `operator+` 的调用都会分配和返回新的临时 `string` 对象（右值）。 `operator+` 不能将一个字符串追加到另一个字符串，因为它不知道源字符串是左值还是右值。 如果两个源字符串都是左值，则它们可能会在程序中的其他位置引用，因此不能修改。 利用右值引用，可以将 `operator+` 修改为采用右值，而右值不能在程序中的其他位置引用。 因此，`operator+`现在可将一个字符串追加到另一个字符串。 这可以显著减少 `string` 类必须执行的动态内存分配的数量。

若要更好地了解移动语义，请考虑将元素插入 `vector` 对象的示例。 如果超出 `vector` 对象的容量，则 `vector` 对象必须为其元素重新分配内存，然后将所有元素复制到其他内存位置，以便为插入的元素腾出空间。 当插入操作复制某个元素时，它将创建一个新元素，调用复制构造函数以将数据从上一个元素复制到新元素，然后销毁上一个元素。 利用移动语义，您可以直接移动对象而不必执行成本高昂的内存分配和复制操作。

若要在 `vector` 示例中利用移动语义，则可以编写将数据从一个对象移动到另一个对象的移动构造函数。

# 完美转发

完美转发可减少对重载函数的需求，并有助于避免转发问题。 当您编写采用引用作为其参数的泛型函数，并且该函数将这些参数传递（或转发）给另一个函数时，将会引发转发问题。 例如，如果泛型函数采用 `const T&` 类型的参数，则调用的函数无法修改该参数的值。 如果泛型函数采用 `T&` 类型的参数，则无法使用右值（如临时对象或整数文本）来调用该函数。

通常，若要解决此问题，则必须提供为其每个参数采用 `T&` 和 `const T&` 的重载版本的泛型函数。 因此，重载函数的数量将基于参数的数量呈指数方式增加。 利用右值引用，您可以编写一个版本的函数，该函数可接受任意参数并将其转发给另一个函数，就像已直接调用其他函数一样。+

请考虑以下声明了四个类型 `W`、`X`、`Y` 和 `Z` 的示例。 每个类型的构造函数采用 `const` 和非 `const`左值引用的不同组合作为其参数。

```c++
struct W  
{  
   W(int&, int&) {}  
};  
  
struct X  
{  
   X(const int&, int&) {}  
};  
  
struct Y  
{  
   Y(int&, const int&) {}  
};  
  
struct Z  
{  
   Z(const int&, const int&) {}  
};  
```

假定您要编写生成对象的泛型函数。 以下示例演示了一种编写此函数的方式：

```c++
template <typename T, typename A1, typename A2>  
T* factory(A1& a1, A2& a2)  
{  
   return new T(a1, a2);  
} 
```

以下示例演示了对 `factory` 函数的有效调用：

```c++
int a = 4, b = 5;  
W* pw = factory<W>(a, b);  
```

但是，以下示例不包含对 `factory` 函数的有效调用，因为 `factory` 采用可修改的左值引用作为其参数，但该函数是使用右值调用的：

```c++
Z* pz = factory<Z>(2, 2); 
```

通常，若要解决此问题，您必须为 `A&` 和 `const A&` 的参数的每个组合创建一个重载版本的 `factory`函数。 利用右值引用，您可以编写一个版本的 `factory` 函数，如以下示例所示：

```c++
template <typename T, typename A1, typename A2>  
T* factory(A1&& a1, A2&& a2)  
{  
   return new T(std::forward<A1>(a1), std::forward<A2>(a2));  
}  
```

此示例使用右值引用作为 `factory` 函数的参数。 [std::forward](https://msdn.microsoft.com/zh-cn/library/ee390914) 函数的用途是将 factory 函数的参数转发给模板类的构造函数。

以下示例演示了使用修改后的 `factory` 函数创建 `W`、`X`、`Y` 和 `Z` 类的实例的 `main` 函数。 修改后的 `factory` 函数会将其参数（左值和右值）转发给适当的类构造函数。

```c++
int main()  
{  
   int a = 4, b = 5;  
   W* pw = factory<W>(a, b);  
   X* px = factory<X>(2, b);  
   Y* py = factory<Y>(a, 2);  
   Z* pz = factory<Z>(2, 2);  
  
   delete pw;  
   delete px;  
   delete py;  
   delete pz;  
}  
```

# 将左值强制转换为右值引用

利用 STL [std::move](https://msdn.microsoft.com/zh-cn/library/ee390941) 函数，您可以将某个对象转换对该对象的右值引用。 或者，您也可以使用 `static_cast` 关键字将左值强制转换为右值引用，如以下示例所示：

```c++
// cast-reference.cpp  
// Compile with: /EHsc  
#include <iostream>  
using namespace std;  
  
// A class that contains a memory resource.  
class MemoryBlock  
{  
   // TODO: Add resources for the class here.  
};  
  
void g(const MemoryBlock&)   
{  
   cout << "In g(const MemoryBlock&)." << endl;  
}  
  
void g(MemoryBlock&&)   
{  
   cout << "In g(MemoryBlock&&)." << endl;  
}  
  
int main()  
{  
   MemoryBlock block;  
   g(block);  
   g(static_cast<MemoryBlock&&>(block));  
}  
```

std::move函数可以以非常简单的方式将左值引用转换为右值引用。

std::move是将对象的状态或者所有权从一个对象转移到另一个对象，只是转移，没有内存的搬迁或者内存拷贝。

# 参考

- [MSDN](https://msdn.microsoft.com/zh-cn/library/dd293668)
- [CSDN](https://blog.csdn.net/u011583798/article/details/56488305)