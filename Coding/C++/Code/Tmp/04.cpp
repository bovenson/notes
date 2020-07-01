#include <iostream>

using namespace std;

void pa (string s) {
    cout << &s << endl;
}

void pb (string &s) {
    cout << &s << endl;
}

int main() {
    string sa = "9999";
    string sb = "sdaaaaaaaaaaaaaaaaaaaawerwefsa";
    string *sc;
    cout << sizeof(sc) << endl;
    cout << sizeof(sa) << endl;
    cout << sizeof(sb) << endl;

    cout << &sa << endl;
    pa(sa);
    pb(sa);

    cout << endl;
    cout << &sb << endl;
    return 0;
}
