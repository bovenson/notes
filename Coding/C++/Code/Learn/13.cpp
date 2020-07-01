#include <iostream>
#include <future>
#include <thread>
#include <chrono>

using namespace std;

int main() {
    std::promise<bool> p;
    thread th([&]() {
        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
        cout << "set value true" << endl;
        p.set_value(true);
    });
    th.detach();
    cout << "th detach" << endl;
    p.get_future().wait();
    cout << "exit" << endl;
    return 0;
}
