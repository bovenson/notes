#include <stdio.h>

int reverse(int x) {
    int MAX_INT = 2 ^ 31, MIN_INT = - (2 ^ 31 - 1);

    int carr[20];

    int i;
    bool positive = x > 0 ? true : false;
    if (!positive)
        x = -x;
    for (i = 0; i < 20; ++i)
    {
        carr[i] = 0;
    }
    i = 0;

    while (x != 0) {
        carr[i] = x % 10;
        x /= 10;
        // printf("%d\n", x);
        i += 1;
    }

    for (i = 0; i < 20; ++i) {
        printf("%d\n", carr[i]);
    }
}

int main()
{
    // printf("%d", reverse(-123));
    int x = -1;
    unsigned int y = 2;
    printf("%d", x > y);
}
