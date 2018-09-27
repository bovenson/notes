# sleep

```c++
#include <iostream>
#include <sys/timeb.h>
#include <unistd.h>

using namespace std;

long long getSystemTime() {
    struct timeb t;
    ftime(&t);
    return 1000 * t.time + t.millitm;
}

int main() {
    cout << getSystemTime() << endl;
    sleep(1);				// 秒
    cout << getSystemTime() << endl;
    usleep(1000000);		// 微妙
    cout << getSystemTime() << endl;
    return 0;
}

/** Output
1538040317832
1538040318833
1538040319833
*/
```

# 获取系统时间

```c++
#include <sys/timeb.h>

long long getSystemTime() {
    struct timeb t;
    ftime(&t);
    return 1000 * t.time + t.millitm;
}
```

