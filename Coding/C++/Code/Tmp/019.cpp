#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

struct A {
public:
    int a, b;
};

int cmp(const void *pa, const void *pb) {
    return ((A*)pa)->a - ((A*)pb)->a;
}

int cmpb(const void *pa, const void *pb) {
    return ((A*)pa)->b - ((A*)pb)->b;
}

int main() {
    int cnt = 5;
    A a[cnt];
    for (int i = 0; i < cnt; i++) {
        a[i].a = i;
        a[i].b = cnt -i;
        cout << a[i].a << " " << a[i].b << endl;
    }
    cout << endl;
    qsort(a, cnt, sizeof(a[0]), cmp);
    for (int i = 0; i < cnt; i++) {
        cout << a[i].a << " " << a[i].b << endl;
    }
    
    cout << endl;
    qsort(a, cnt, sizeof(a[0]), cmpb);
    for (int i = 0; i < cnt; i++) {
        cout << a[i].a << " " << a[i].b << endl;
    }
    return 0;
}
