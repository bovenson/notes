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
    sleep(1);
    cout << getSystemTime() << endl;
    usleep(1000000);
    cout << getSystemTime() << endl;
    return 0;
}
