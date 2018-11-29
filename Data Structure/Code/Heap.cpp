#include <iostream>
#include <vector>

using namespace std;

class Heap {
    vector<int> heap;
public:
    Heap() {}
    Heap(vector<int> &nums) : heap(nums) {
        this->make();
    }

    // make heap
    void make() {
        if (heap.size() < 2) return;
        for (int i = heap.size() / 2 - 1; i >= 0; --i)
            adjustDown(i);
    }

    // pop_heap
    int pop() {
        if (heap.size() > 0) {
            swap(heap[0], heap.back());
            int res = heap.back();
            heap.pop_back();
            adjustDown(0);
            return res;
        } else {
            cout << "[ERROR] empty heap" << endl;
            throw "[ERROR] empty heap";
        }
    }

    // push heap
    void push(int value) {
        heap.push_back(value);
        adjustUp(heap.size() - 1);
    }

    // sort_heap
    vector<int> sort() {
        vector<int> backup = heap, res;
        while(heap.size() > 0) {
            res.push_back(this->pop());
        }
        heap = backup;
        return res;
    }

    // support functions
    void adjustUp(int pos) {
        int parent = (pos - 1) / 2;
        while (parent >= 0 && heap[parent] < heap[pos]) {
            swap(heap[parent], heap[pos]);
            pos = parent;
            parent = (pos - 1) / 2;
        }
    }

    void adjustDown(int pos) {
        int hole = pos * 2 + 1;
        while (hole < heap.size()) {
            if (hole + 1 < heap.size() && heap[hole] < heap[hole + 1]) ++hole;

            if (heap[hole] > heap[pos]) {
                swap(heap[hole], heap[pos]);
                pos = hole;
                hole = pos * 2 + 1;
            } else {
                break;
            }
        }
    }

    void display() {
        for (auto &item : heap) {
            cout << item << " ";
        }
        cout << endl;
    };
};

void displayVector(vector<int> &&v) {
    for (auto &item : v) {
        cout << item << " ";
    }
    cout << endl;
}

int main() {
    Heap heap;
    int op, value;
    while (cin >> op) {
        if (op == 0) {
            cout << "pop element" << endl;
            heap.pop();
        } else if (op == 1) {
            cout << "push element" << endl;
            cin >> value;
            heap.push(value);
        } else {
            break;
        }
        cout << "display: ";
        heap.display();
        cout << "sort: ";
        displayVector(heap.sort());
    }
    return 0;
}
