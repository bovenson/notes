#include <iostream>
#include <stdlib.h>

using namespace std;

int cmp(const void *a, const void *b) {
    return 1;
    const int *c = *(const int**)a;
    const int *d = *(const int**)b;
    cout << c[0] << " - " << d[0] << endl;
    return c[0]- d[0];
}

int main() {
    int arr[7][2] = {1, 2, 3, 2, 4, 5, 1, 2, -1, 2, 9, 1, 0, 1};
    qsort(arr, 7, sizeof(int) * 2, cmp);
    return 0;
}
