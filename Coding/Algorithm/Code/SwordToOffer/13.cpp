/**
 * 机器人的移动范围
 * */
#include <iostream>
#include <string.h>
using namespace std;

void solve(int threshold, int rows, int cols, bool *visited, int row, int col, int *count) {
    if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row * cols + col]) {
        return;
    }
    int trow = row, tcol = col;
    int thresholdCount = 0;
    while (trow > 0) {
        thresholdCount += trow % 10;
        trow /= 10;
    }
    while (tcol > 0) {
        thresholdCount += tcol % 10;
        tcol /= 10;
    }
    if (thresholdCount > threshold) {
        return;
    }
    *count += 1;
    visited[row * cols + col] = true;
    solve(threshold, rows, cols, visited, row + 1, col, count);
    solve(threshold, rows, cols, visited, row - 1, col, count);
    solve(threshold, rows, cols, visited, row, col + 1, count);
    solve(threshold, rows, cols, visited, row, col - 1, count);
}

int moveCount (int threshold, int rows, int cols) {
    if (rows <= 0 || cols <= 0) {
        return 0;
    }

    int *count = new int(0);
    bool *visited = new bool[rows * cols];
    memset(visited, 0, sizeof(bool) * rows * cols);
    solve(threshold, rows, cols, visited, 0, 0, count);
    delete[] visited;
    return *count;
}

int main () {
    cout << moveCount(100, 3, 3) << endl;
    return 0;
}
