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

# IO

## 从标准输入读取二进制流

```c++
if (std::freopen(nullptr, "rb", stdin) == nullptr || std::ferror(stdin)) {
    throw std::runtime_error(std::strerror(errno));
}
```

```c++
const std::size_t INIT_BUFFER_SIZE = 1024;

int main()
{
    try
    {
        // on some systems you may need to reopen stdin in binary mode
        // this is supposed to be reasonably portable
        std::freopen(nullptr, "rb", stdin);

        if(std::ferror(stdin))
            throw std::runtime_error(std::strerror(errno));

        std::size_t len;
        std::array<char, INIT_BUFFER_SIZE> buf;

        // somewhere to store the data
        std::vector<char> input;

        // use std::fread and remember to only use as many bytes as are returned
        // according to len
        while((len = std::fread(buf.data(), sizeof(buf[0]), buf.size(), stdin)) > 0)
        {
            // whoopsie
            if(std::ferror(stdin) && !std::feof(stdin))
                throw std::runtime_error(std::strerror(errno));

            // use {buf.data(), buf.data() + len} here
            input.insert(input.end(), buf.data(), buf.data() + len); // append to vector
        }

        // use input vector here
    }
    catch(std::exception const& e)
    {
        std::cerr << e.what() << '\n';
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}
```

- [Ref 1](https://stackoverflow.com/questions/39757354/best-way-to-read-binary-file-c-though-input-redirection)
- [Ref 2](https://stackoverflow.com/questions/5420317/reading-and-writing-binary-file)