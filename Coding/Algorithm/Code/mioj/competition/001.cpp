/**
@autor	sunzhenkai
@file	001.cpp
@date	2019-02-28 19:14:29
@desc	小米兔的轨迹
*/

#include <iostream>
#include <vector>

using namespace std;


class Direction {
    static vector<Direction> dv;
    public:
    int x, y;
    Direction(int x_, int y_) {
        x = x_;
        y = y_;
    }

    Direction go(int d) {
        return Direction(x + dv[d].x, y + dv[d].y);
    }

    bool isLegal(int n, int m) {
        if (x < 0 || y < 0 || x >= n || y >= m) {
            return false;
        }
        return true;
    }
};

vector<Direction> Direction::dv = {Direction(0, 1), Direction(1, 0), Direction(0, -1), Direction(-1, 0)}; 

int main() {
    int n, m, v;
    cin >> n >> m;
    vector<vector<int> > matrix, mv;
    
    for (int i = 0; i < n; ++i) {
        vector<int> in, mvin;
        for (int j = 0; j < m; ++j) {
            cin >> v;
            in.push_back(v);
            mvin.push_back(0);
        }
        matrix.push_back(in);
        mv.push_back(mvin);
    }

    int a = 0, b = 0, c = 0, d = 0;
    vector<int> res;
    Direction curPos(0, 0);
    while (c < n * m) {
        mv[curPos.x][curPos.y] = 1;
        c++;
        res.push_back(matrix[curPos.x][curPos.y]);
        auto nextPos = curPos.go(d);
        if (!nextPos.isLegal(n, m) || mv[nextPos.x][nextPos.y]) {
            d = (d + 1) % 4;
            curPos = curPos.go(d);
        } else {
            curPos = nextPos;
        }
    }
    for (size_t i=0; i < res.size(); ++i) {
        cout << res[i];
        if (i < res.size() - 1u) {
            cout << " ";
        }
    }
    return 0;
}