#include <stdio.h>

int main()
{
    char cStart = 'd';
    for (int i=0; i < 3; ++i)   // A d e f
    {
        for (int j=0; j < 3; ++j)   // B d e f
        {
            for (int k=0; k < 3; ++k)
            {
                for (int l=0; l < 3; ++l)
                {
                    if (i != j)
                    {
                        printf("%c%d %c%d\n", cStart+i, k+1, cStart+j, l+7);
                    }
                }
            }
        }
    }
    return 0;
}
