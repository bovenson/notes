#include <iostream>
#include <thread>

using namespace std;

void fb() {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    cout << "fb() done" << endl;
}

// void f(thread thd) {  由于thread禁用了赋值操作符，这里编译时会报错
void f(thread &thd) {
    cout << thd.get_id() << endl;
    thd.join();
}

void fc(thread thd) {
    cout << thd.get_id() << endl;
    thd.join();
}

int main() {
    thread th1(fb);
    f(th1);
    thread th2(fb);
    cout << th1.joinable() << endl;
    cout << th2.joinable() << endl;
    fc(std::move(th2));
    cout << th1.joinable() << endl;
    cout << th2.joinable() << endl;
    return 0;
}
