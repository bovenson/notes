#include <iostream>
#include "utils.h"

using namespace std;

class Solution {
    vector<vector<int>> dirSteps = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<int> v(n, 0);
        vector<vector<int>> res(n, v);
        int dir = 0, x = 0, y = 0;

        for (int i=1; i <= n * n; ++i) {
            res[y][x] = i;

            int nx = x + dirSteps[dir][0];
            int ny = y + dirSteps[dir][1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || res[ny][nx] != 0) {
                dir = (dir + 1) % 4;
                nx = x + dirSteps[dir][0];
                ny = y + dirSteps[dir][1];
            }

            x = nx;
            y = ny;
        }

        return res;
    }
};

int main() {
    Solution s;
    auto res = s.generateMatrix(0);
    printVector(res);
    return 0;
}
