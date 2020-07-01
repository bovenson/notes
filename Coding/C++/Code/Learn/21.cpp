#include <iostream>
#include <vector>
#include <condition_variable>
#include <thread>
#include <chrono>

using namespace std;

std::condition_variable cv;
std::mutex cv_m;

int i = 0;

void waits_no_judge() {
    std::unique_lock<std::mutex> lk(cv_m);
    std::cout << "Waiting..." << std::endl;
    cv.wait(lk, [] { return i == 1; });
    std::cout << "...finished waiting. i == 1" << std::endl;
}

void waits_with_judge() {
    std::unique_lock<std::mutex> lk(cv_m);
    std::cout << "Waiting..." << std::endl;
    cv.wait(lk);
    std::cout << "...finished waiting." << std::endl;
}

void send_signal() {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    std::cout << "Notifying fir time" << std::endl;
    cv.notify_all();

    std::this_thread::sleep_for(std::chrono::seconds(1));
    {
        std::lock_guard<std::mutex> lk(cv_m);
        std::cout << "Notifying sec time" << std::endl;
    }
    cv.notify_all();

    std::this_thread::sleep_for(std::chrono::seconds(1));
    {
        std::lock_guard<std::mutex> lk(cv_m);
        i = 1;
        std::cout << "Notifying thr time" << std::endl;
    }
    cv.notify_all();
}

int main() {
    std::thread th1(waits_no_judge), th2(waits_no_judge), th3(waits_with_judge), th4(waits_with_judge), th5(send_signal);
    th1.join();
    th2.join();
    th3.join();
    th4.join();
    th5.join();
    return 0;
}
