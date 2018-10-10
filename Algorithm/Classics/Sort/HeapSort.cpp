#include <iostream>

using namespace std;

void swap(int &a, int &b) {
    int t = a;
    a = b;
    b = t;
}

void heapify(int arr[], int n, int cur) {
    int next;
    while (2 * cur + 1 < n) {
        next = 2 * cur + 1;
        
        if (next + 1 < n && arr[next] < arr[next+1]) 
            ++next;
        
        if (arr[cur] < arr[next]) {
            swap(arr[cur], arr[next]);
        } else {
            break;
        }
        
        cur = next;
    }
}

void heapSort(int arr[], int n) {
    for (int i = n / 2 - 1; i >= 0; --i) {
        heapify(arr, n, i);
    }
    for (int i = n-1; i > 0; --i) {
        swap(arr[0], arr[i]);
        heapify(arr, i, 0);
    }
}

int main() {
    int arr[] = {1, -1, 0, 19, 5, 8, 3, 2, 1}, n = 9;
    heapSort(arr, n);
    for (int i = 0; i < n; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;
    return 0;
}
