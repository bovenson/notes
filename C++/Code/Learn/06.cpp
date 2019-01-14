#include <iostream>
#include <mutex>
#include <thread>
#include <unistd.h>

using namespace std;

class LockTest {
    public:
    std::mutex m_, n_;

    void fa();
    void fb();
};

void LockTest::fa() {
    unique_lock<mutex> lock(m_);
    int c = 0;
    while (true) {
        ++c;
        cout << "foo" << endl;
        sleep(1);
    }
}

void LockTest::fb() {
    unique_lock<mutex> lock(m_);
    int c = 0;
    while (1) {
        ++c;
        cout << "bar" << endl;
        sleep(1);
    }
}

int main() {
    LockTest lt;
    thread threadFoo(&LockTest::fa, &lt);
    thread threadBar(&LockTest::fb, &lt);
    threadFoo.join();
    threadBar.join();
    return 0;
}
