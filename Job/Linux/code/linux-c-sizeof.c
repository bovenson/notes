/*************************************************************************
	> File Name: linux-c-sizeof.c
	> Author: bovenson
	> Email:  szhkai@126.com
	> Created Time: 2017-09-13 15:52:15
 ************************************************************************/

#include <stdio.h>

int main()
{
	int intValue = 1024;
	char str[] = "hello";
	const char* ch = str;
	printf("%d\n", sizeof(intValue));
	printf("%d\n", sizeof(str));
	printf("%d\n", sizeof(ch));
	return 0;
}

