#include <iostream>

using namespace std;

class A {
public:
    // static int a = 1;            // ERROR: ISO C++ 禁止在类内初始化非 const static 成员变量
    const int b = 1;            
    const static int c = 1;         // OK
    // const static double d = 1.1; // ERROR: 禁止在类内初始化非整形 const static 成员变量
    const int e;                    // WARNING: 必须初始化; 这里 OR 构造函数初始化列表
    const double f = 1.1;
    A () : e(1) {
        // e = 3;                   // 常量不可赋值 必须在定义时初始化
    }
    
    void fc (int v) {
        const int fc = v;
        const static int fcs = v;
        cout << fc << endl;
        cout << fcs << endl;
    }
};

int main() {
    A a;
    a.fc(1);
    A b;
    b.fc(2);
    return 0;
} /** Output
1
1
2
1
*/
