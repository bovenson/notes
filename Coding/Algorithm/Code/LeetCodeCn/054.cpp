#include <iostream>
#include "utils.h"

using namespace std;

class Solution {
    vector<vector<int>> dirStep = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    inline bool isValidPt(int &c, int &r, int &x, int &y, vector<bool> &v) {
        if (x < 0 || x >= c || y < 0 || y >= r || v[y * c + x] == true)
            return false;
        return true;
    }

public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> res;
        if (matrix.size() == 0)
            return res;

        int r = matrix.size(), c = matrix[0].size();
        vector<bool> v(r * c, false);
        int dir = 0, x = 0, y = 0;
        while (true) {
            res.emplace_back(matrix[y][x]);
            v[y * c + x] = true;
            
            int nx = x + dirStep[dir][0];
            int ny = y + dirStep[dir][1];
            
            if (!isValidPt(c, r, nx, ny, v)) {    
                dir = (dir + 1) % 4;
                nx = x + dirStep[dir][0];
                ny = y + dirStep[dir][1];
            }

            if (!isValidPt(c, r, nx, ny, v))  
                break;

            x = nx;
            y = ny;
        }
        return res;
    }
};

int main() {
    // vector<vector<int>> matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    // vector<vector<int>> matrix = {{1}, {2}, {3}};
    vector<vector<int>> matrix = {};
    Solution s;
    printVector(s.spiralOrder(matrix));
    return 0;
}
