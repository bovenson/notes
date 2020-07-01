#include <stdio.h>

/*
 * fork test
 * */
int main()
{
    int i;
    for (i = 0; i < 3; ++i)
    {
        printf("*\n");
        fork();
    }
    return 0;
}
