#include <iostream>
#include <string.h>
#include "utils.h"

using namespace std;

class SolutionB {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> res;
        vector<string> sted;
        for (auto str: strs) {
            bool flag = false;
            string strSorted = str; 
            sort(strSorted.begin(), strSorted.end());
            for (int i=0; i < res.size(); ++i) {
                auto &tr = res[i];
                if (sted[i].compare(strSorted) == 0) {
                    tr.push_back(str);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                vector<string> tr;
                sted.emplace_back(strSorted);
                tr.emplace_back(str);
                res.emplace_back(tr);
            }
        }
        return res;
    }
};

class SolutioniA {
public:
    bool isAnagramsA(string &a, string &b) {
        if (a.length() != b.length())
            return false;

        int ca[2<<8];
        memset(ca, 0, sizeof(ca));

        for (int i=0; i < a.length(); ++i) 
            ++ca[a[i]];

        for (int i=0; i < b.length(); ++i) 
            --ca[b[i]];

        for (int i=0; i < (2<<8); ++i)
            if (ca[i] != 0)
                return false;

        return true;
    }

    bool isAnagrams(string a, string b) {
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        return a.compare(b) == 0;
    }

    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> res;
        
        for (auto &str: strs) {
            bool flag = false;
            for (auto &tr: res) {
                if (isAnagrams(str, tr[0])) {
                    flag = true;
                    tr.emplace_back(str);
                    break;
                }
            }
            if (!flag) {
                vector<string> tr;
                tr.emplace_back(str);
                res.emplace_back(tr);
            }
        }

        return res;
    }
};

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> res;
        map<string, vector<string>> mp;
        for (auto &str: strs) {
            string k = str;
            sort(k.begin(), k.end());
            mp[k].emplace_back(str);
        }

        for (const auto &pr: mp) 
            res.emplace_back(pr.second);

        return res;
    }
};


int main() {
    Solution s;

    string a = "abc";
    string b = "baca";
    // cout << s.isAnagrams(a, b) << endl;
    
    vector<string> ips;
    string ip;
    while (cin >> ip) {
        ips.emplace_back(ip);
    }
    auto res = s.groupAnagrams(ips);
    for (auto v: res)
        printVector(v);
    return 0;
}
