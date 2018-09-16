#include <iostream>

using namespace std;

int* buildNext(char* P) //构造模式串P的next表
{
    size_t m = strlen(P), j = 0;
    int* N = new int[m];//next表
    int t = N[0] = -1;//模式串指针
    while (j < m - 1)
    {
        if (0 > t || P[j] == P[t])//匹配
        {
            j++; t++;
            N[j] = t;
        }
        else //匹配失败
            t = N[t];
    }
}

int match(char* P, char* T, bool &v)
{
    int* next = buildNext(P);         //构造next表
    int n = (int)strlen(T), i = 0;    //文本串长度n，文本串指针i
    int m = (int)strlen(P), j = 0;    //模式串长度m，文本串指针j

    while (i < n) {
        int ti = i;
        while (j < m)            //自左向右逐个比较字符
        {
            if(0 > j || T[i] == P[j])     //若匹配成功，或P已经移出最左侧（即j==-1），（注：两个判断顺序不能交换）
            {
                i++; j++;                 //移到下一字符
            }
            else
                j = next[j];              //模式串根据next表右移，而文本串不必回退
        }
    }

    delete []next;  //释放next表
    return i - j;   //如果匹配成功，i-j为P相对于T的对齐位置；如果匹配失败，i-j必然>n-m
}

int solve(string &S, string &P) {
    bool v[S.length()];
    memset(v, 0, sizeof(bool) * S.length());
    match(S.c_str(), P.c_str(), v);
}

int main () {
    string S, P;
    cin >> S >> P;
    solve(S, P);
    return 0;
}


