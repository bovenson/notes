#include <iostream>
#include <thread>
#include <pthread.h>

using namespace std;

void f() {
    std::this_thread::sleep_for(std::chrono::seconds(3));
}

int main() {
    std::thread th(f);
    thread::native_handle_type handle = th.native_handle();
    cout << "ID: " << handle << endl;
    cout << "Handle: " << th.get_id() << endl;
    th.join();
    cout << "Handle: " << th.native_handle() << endl;
    pthread_cancel(th.native_handle());
    return 0;
}
