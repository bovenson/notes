#include <iostream>
#include <map>

using namespace std;

class Solution {
public:
    string intToRoman(int num) {
        static int val[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        static string sym[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int div, inx = 0;
        string res;
        while (num > 0) {
            div = num / val[inx];
            num -= div * val[inx];
            while (div > 0) {
                res += sym[inx];
                div--;
            }
            inx++;
        }
        return res;
    }
};

int main() {
    Solution s;
    cout << s.intToRoman(58) << endl;
    cout << s.intToRoman(1994) << endl;
    return 0;
}
