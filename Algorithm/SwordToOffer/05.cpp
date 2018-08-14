/*
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * */
#include <iostream>
#include <string.h>

using namespace std;

class Solution {
    public:
        void replaceSpace(char *str, int length) {
            int cnt = 0;
            for (int i=0; i < length; ++i) {
                if (str[i] == ' ') {
                    ++cnt;
                }
            }
            int j = length-1, k = length+cnt*2;
            str[k--] = '\0';
            for (; j >= 0; --j) {
                if (str[j] != ' ') {
                    str[k--] = str[j];
                } else {
                    k -= 3;
                    str[k+1] = '%';
                    str[k+2] = '2';
                    str[k+3] = '0';
                }
            }
        }
};

int main() {
    Solution s;
    char str[] = "Hello World  ";
    s.replaceSpace(str, 11);
    cout<<str<<endl;
}
