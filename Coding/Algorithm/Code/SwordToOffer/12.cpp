/**
 * 矩阵中的路径
 * */
#include <stdio.h>
#include <iostream>
#include "string.h"

using namespace std;

bool solve (char *matrix, int rows, int cols, const string &path, bool *visited, int cur, int row, int col) {
    if (row < 0 || col < 0 || visited[row * cols + col]){
        return false;
    }

    if (cur == path.length()) {
        return true;
    }

    if (path[cur] != matrix[row * cols + col] || row >= rows || col >= cols) {
        return false;
    }
    
    visited[row * cols + col] = true;

    bool res = solve(matrix, rows, cols, path, visited, cur + 1, row, col + 1) || 
        solve(matrix, rows, cols, path, visited, cur + 1, row, col - 1) ||
        solve(matrix, rows, cols, path, visited, cur + 1, row + 1, col) ||
        solve(matrix, rows, cols, path, visited, cur + 1, row - 1, col);

    visited[row * cols + col] = false;
    return res;
}

bool hasPath (char *matrix, int rows, int cols, const string &path) {
    bool *visited = new bool[rows * cols];

    memset(visited, 0, rows * cols);
    if (matrix == nullptr || rows < 1 || cols < 1) {
        return false;
    }
    for (int i=0; i < rows; ++i) {
        for (int j=0; j < cols; ++j) {
            if (solve(matrix, rows, cols, path, visited, 0, i, j)) {
                return true;
            };
        }
    }
    delete visited;
    return false;
}

int main() {
    string path = "bfce";
    char matrix[] = {'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h'};
    cout << hasPath(matrix, 3, 4, path) << endl;
    path = "abfb";
    cout << hasPath(matrix, 3, 4, path) << endl;
    return 0;
}
