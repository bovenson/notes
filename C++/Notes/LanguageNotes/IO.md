---
title: C++ IO
tags:
	- IO
categories:
	- C++
---

# File

## 读取文件到String

```从++
#include <string>
#include <fstream>
#include <streambuf>

std::ifstream t("file.txt");
std::string str((std::istreambuf_iterator<char>(t)),
                 std::istreambuf_iterator<char>());
```

```c++
ifstream inFile;
inFile.open(inFileName);//open the input file

stringstream strStream;
strStream << inFile.rdbuf();//read the file
string str = strStream.str();//str holds the content of the file

cout << str << endl;//you can do anything with the string!!!
```

## 写String到文件

```c++
#include <iostream>
#include <fstream>

string jsonStr = "some string";
ofstream out("out.json", ofstream::out | ofstream::trunc);
if (out.is_open()) {
    out << jsonStr;
} else {
    cout << "ERROR: open file failed" << endl;
}
```

**参考**

- [StackOverflow](https://stackoverflow.com/questions/2602013/read-whole-ascii-file-into-c-stdstring)