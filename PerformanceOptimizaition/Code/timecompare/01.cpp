#include <iostream>
#include <sys/timeb.h>
#include <vector>
#include <set>
#include <map>
#include <unordered_map>
#include <unordered_set>
#include <random>
#include <climits>
#include "sstream"

std::random_device rd;
std::mt19937 rng(rd());

std::uniform_int_distribution<int> uni(1, 100);
std::uniform_int_distribution<int> uni_int(INT_MIN, INT_MAX);

using namespace std;

long long getSystemTime() {
  timeb t;
  ftime(&t);
  return 1000 * t.time + t.millitm;
}

string getRandStr(int len) {
    static string seedStr = "01234567890qwertyuiop[]asdfghjkl;'zxcvbnm,./";
    static std::uniform_int_distribution<int> uni_s(0, seedStr.length()-1);
    string res;
    res.reserve(len);
    for (int i = 0; i < len; ++i) {
        res += seedStr[uni_s(rng)];
    }
    return res;
}

static uint64_t MurmurHash64(const void *key, int len, uint64_t seed) {
    const uint32_t m = 0x5bd1e995;
    const int r = 24;

    uint32_t h1 = uint32_t(seed) ^len;
    uint32_t h2 = uint32_t(seed >> 32);

    const uint32_t *data = (const uint32_t *) key;

    while (len >= 8) {
    uint32_t k1 = *data++;
    k1 *= m;
    k1 ^= k1 >> r;
    k1 *= m;
    h1 *= m;
    h1 ^= k1;
    len -= 4;

    uint32_t k2 = *data++;
    k2 *= m;
    k2 ^= k2 >> r;
    k2 *= m;
    h2 *= m;
    h2 ^= k2;
    len -= 4;
    }

    if (len >= 4) {
    uint32_t k1 = *data++;
    k1 *= m;
    k1 ^= k1 >> r;
    k1 *= m;
    h1 *= m;
    h1 ^= k1;
    len -= 4;
    }

    switch (len) {
    case 3:
        h2 ^= ((unsigned char *) data)[2] << 16;
    case 2:
        h2 ^= ((unsigned char *) data)[1] << 8;
    case 1:
        h2 ^= ((unsigned char *) data)[0];
        h2 *= m;
    };

    h1 ^= h2 >> 18;
    h1 *= m;
    h2 ^= h1 >> 22;
    h2 *= m;
    h1 ^= h2 >> 17;
    h1 *= m;
    h2 ^= h1 >> 19;
    h2 *= m;

    uint64_t h = h1;
    h = (h << 32) | h2;
    return h;
}

std::string toString(long long num) {
    std::ostringstream ss;
    ss << num;

    return ss.str();
}

int main() {
    long long dataCount = 10000 * 100;
    long long repeat = 10000 * 1000 * 1;
    vector<pair<string, long long>> interval;
    long long tInterval;

    long long timeStamp = getSystemTime();
    for (int i = 0; i < repeat; ++i) {}
    interval.push_back(pair<string, long long>("CPU空跑repeat次", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (int i = 0; i < dataCount; ++i) {}
    interval.push_back(pair<string, long long>("CPU空跑dataCount次", getSystemTime() - timeStamp));

    vector<int> randIntVec;
    vector<string> randStrVec;
    set<int> sint;
    set<string> sstr;
    unordered_set<int> uosint;
    unordered_set<string> uosstr;
    map<int, int> mint;
    map<string, int> mstr;
    unordered_map<int, int> uomint;
    unordered_map<string, int> uomstr;

    timeStamp = getSystemTime();
    for (int i = 0; i < dataCount; ++i) {
        int randInt = uni_int(rng);
        int randLen = uni(rng);
        string randStr = getRandStr(randLen);
        randIntVec.push_back(randInt);
        randStrVec.push_back(randStr);
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "生成数据: " << tInterval << endl;
    interval.push_back(pair<string, long long>("生成数据", tInterval));

    timeStamp = getSystemTime();
    // set int
    for (auto tint : randIntVec) {
        sint.insert(tint);
    }
    interval.push_back(pair<string, long long>("生成 set int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // unordered_set int
    for (auto tint : randIntVec) {
        uosint.insert(tint);
    }
    interval.push_back(pair<string, long long>("生成 unordered_set int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // set string
    for (auto tstr : randStrVec) {
        sstr.insert(tstr);
    }
    interval.push_back(pair<string, long long>("生成 set string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // unordered_set string
    for (auto tstr : randStrVec) {
        uosstr.insert(tstr);
    }
    interval.push_back(pair<string, long long>("生成 unordered_set string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // map int
    for (auto tint : randIntVec) {
        mint.insert(pair<int, int>(tint, 0));
    }
    interval.push_back(pair<string, long long>("生成 map int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // unordered_map int
    for (auto tint : randIntVec) {
        uomint.insert(pair<int, int>(tint, 0));
    }
    interval.push_back(pair<string, long long>("生成 unordered_map int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // map string
    for (auto tstr : randStrVec) {
        mstr.insert(pair<string, int>(tstr, 0));
    }
    interval.push_back(pair<string, long long>("生成 map string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    // unordered_map string
    for (auto tstr : randStrVec) {
        uomstr.insert(pair<string, int>(tstr, 0));
    }
    interval.push_back(pair<string, long long>("生成 unordered_map string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randIntVec) {
        sint.find(v);
    }
    interval.push_back(pair<string, long long>("检索 set int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randIntVec) {
        uosint.find(v);
    }
    interval.push_back(pair<string, long long>("检索 unordered_set int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randStrVec) {
        sstr.find(v);
    }
    interval.push_back(pair<string, long long>("检索 set string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randStrVec) {
        uosstr.find(v);
    }
    interval.push_back(pair<string, long long>("检索 unordered_set string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randIntVec) {
        mint.find(v);
    }
    interval.push_back(pair<string, long long>("检索 map int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randIntVec) {
        uomint.find(v);
    }
    interval.push_back(pair<string, long long>("检索 unordered_map int", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randStrVec) {
        mstr.find(v);
    }
    interval.push_back(pair<string, long long>("检索 map string", getSystemTime() - timeStamp));

    timeStamp = getSystemTime();
    for (auto v : randStrVec) {
        uomstr.find(v);
    }
    interval.push_back(pair<string, long long>("检索 unordered_map string", getSystemTime() - timeStamp));

    for (auto &pr : interval) {
        cout << pr.first << ": " << pr.second << endl;
    }
    return 0;
}
