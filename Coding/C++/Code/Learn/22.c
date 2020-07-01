#include <stdio.h>
#include <string.h>

int main() {
    char *c = "hello.world";
    size_t len = strlen(c);
    printf("%s\n", memchr(c, '.', len));
    // printf("%s\n", memchr(c, 'z', len));
    char* p = memchr(c, 'z', len);
    printf("%d\n", p);
    return 0;
}
