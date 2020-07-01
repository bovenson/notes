#include <iostream>
#include <thread>
#include <unistd.h>
#include <fstream>

using namespace std;

void f() {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    cout << "f() finish" << endl;
    ofstream out("out", ofstream::out | ofstream::trunc);
    if (out.is_open()) {
        out << "HELLO";
    }
    out.close();
    return;
}

int main() {
    std::thread ths[5];
    ths[0] = std::thread();
    cout << ths[0].joinable() << endl;  // 0
    // ths[0].join();                      // terminate called after throwing an instance of 'std::system_error'; Invalid argument
    ths[1] = std::thread(f);

    ths[1].join();
    cout << "main() finish" << endl;
    return 0;
}
