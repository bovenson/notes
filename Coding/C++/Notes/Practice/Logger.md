---
title: Logger
---

# 源码

```c++
#include <iostream>
#include <sstream>
#include <vector>

const int DEBUG = 0;
const int INFO = 1;
const int WARNING = 2;
const int ERROR = 3;
const int FATAL = 4;
const int NUM_SEVERITIES = 5;
const char *serveritys_[] = {"DEBUG", "INFO", "WARNING", "ERROR", "FATAL"};

using namespace std;

class Logger : public std::basic_ostringstream<char> {
    public:
    Logger(const char* fname, int line, int serverity);
    ~Logger();

    void GenerateLogMsg();

    private:
    const char* fname_;
    int line_;
    int serverity_;
};

Logger::Logger(const char* fname, int line, int serverity) {
    this->fname_ = fname;
    this->line_ = line;
    this->serverity_ = serverity;
}


Logger::~Logger() {
    GenerateLogMsg();
}

#define _LOG_FATAL Logger(__FILE__, __LINE__, FATAL)
#define _LOG_DEBUG Logger(__FILE__, __LINE__, DEBUG)
#define _LOG_ERROR Logger(__FILE__, __LINE__, ERROR)
#define _LOG_INFO Logger(__FILE__, __LINE__, INFO)
#define _LOG_WARNING Logger(__FILE__, __LINE__, WARNING)
#define LOG(serverity) _LOG_##serverity

void Logger::GenerateLogMsg() {
    fprintf(stderr, "[%s:%d] [%s] %s\n", fname_, line_, serveritys_[serverity_], str().c_str());
}

int main() {
    LOG(DEBUG) << "HELLO LOG";
    return 0;
}
```

# 运行

```shell
$ g++ 22.cpp -std=c++17 && ./a.out 
[22.cpp:51] [DEBUG] HELLO LOG
```

