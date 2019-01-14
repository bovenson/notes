#include <iostream>
#include <unistd.h>
#include <thread>

using namespace std;

int main() {
    thread th([]() {
        int i = 0;
        while (true) {
            cout << "- " << i++ << " -" << endl;
            sleep(2);
        }
    });
    th.join();
    return 0;
}
