#include <iostream>
using namespace std;

struct  {
    int level;
    int commision;
} Bind;

int solve(int level[], int commision[], int worker) {
    Bind binds[sizeof(level)];
    for (int i = 0; i < sizeof(level); ++i) {
            binds[i] = {level[i], commision[i]};
    }
    qsort(binds, 0, sizeof())
}