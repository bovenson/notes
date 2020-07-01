#include <iostream>
#include <thread>
#include <pthread.h>

using namespace std;

void f() {
    std::this_thread::sleep_for(std::chrono::seconds(2));
    cout << "f(): mid check" << endl;
    std::this_thread::sleep_for(std::chrono::seconds(2));
    cout << "f(): done" << endl;
}

void fl() {
    while (true) {}
    cout << "fl() done" << endl;
}

int main() {
    std::thread th(f);
    thread::native_handle_type handle = th.native_handle();
    th.detach();
    std::this_thread::sleep_for(std::chrono::seconds(3));
    pthread_cancel(handle);

    std::thread thfl(fl);
    thfl.detach();
    handle = thfl.native_handle();
    cout << "Handle: " << handle << endl;
    pthread_cancel(handle);
    std::this_thread::sleep_for(std::chrono::seconds(3));

    return 0;
}
