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

#include "cs.cpp"

std::random_device rd;
std::mt19937 rng(rd());

std::uniform_int_distribution<int> uni(1, 20);
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
    string * t;
    cout << sizeof(t) << endl;
    cout << sizeof(BaseFeatureV1) << endl;
    cout << sizeof(BaseFeatureV2) << endl;
    long long dataCount = 10000 * 1000;
    long long repeat = 10000 * 10000 * 1;
    vector<pair<string, long long>> interval;
    long long tInterval, timeStamp;

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

    // 内存分配 & 内存拷贝 & 拼接字符串
    timeStamp = getSystemTime();
    for (auto v : randStrVec) {
        string *t = new string(v);
        delete t;
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "内存分配(new): " << tInterval << endl;
    interval.push_back(pair<string, long long>("内存分配(new)", tInterval));

    timeStamp = getSystemTime();
    for (auto v : randStrVec) {
        string t = v;
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "内存拷贝: " << tInterval << endl;
    interval.push_back(pair<string, long long>("内存拷贝", tInterval));

    timeStamp = getSystemTime();
    int randStrVecLen = randStrVec.size();
    for (int i = 1; i < randStrVecLen; ++i) {
        string t = randStrVec[i] + randStrVec[1];
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "字符串拼接: " << tInterval << endl;
    interval.push_back(pair<string, long long>("字符串拼接", tInterval));

    timeStamp = getSystemTime();
    for (auto tStr : randStrVec) {
        MurmurHash64(tStr.c_str(), tStr.length(), 11);
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "MurMurHash: " << tInterval << endl;
    interval.push_back(pair<string, long long>("MurMurHash", tInterval));

    timeStamp = getSystemTime();
    for (auto tInt : randIntVec) {
        toString(tInt);
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "toString: " << tInterval << endl;
    interval.push_back(pair<string, long long>("toString", tInterval));

    string buff; 
    buff.reserve(1024);

    timeStamp = getSystemTime();
    for (int i = 0; i < repeat; ++i) {
      buff.clear();
      BaseFeatureV1 baseFeature;
      buff += randIntVec[0];
      buff += randIntVec[1];
      buff += randIntVec[2];
      baseFeature.identifier = MurmurHash64(buff.c_str(), buff.size(), 11);
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "BaseFeatureV1: " << tInterval << endl;
    interval.push_back(pair<string, long long>("toString", tInterval));

    timeStamp = getSystemTime();
    for (int i = 0; i < repeat; ++i) {
      buff.clear();
      BaseFeatureV2 baseFeature;
      buff += randIntVec[0];
      buff += randIntVec[1];
      buff += randIntVec[2];
      baseFeature.identifier = MurmurHash64(buff.c_str(), buff.size(), 11);
    }
    tInterval = getSystemTime() - timeStamp;
    cout << "BaseFeatureV1: " << tInterval << endl;
    interval.push_back(pair<string, long long>("toString", tInterval));

    return 0;
}

/** Output
8
72
16
生成数据: 6935
内存分配(new): 1741
内存拷贝: 906
字符串拼接: 1259
MurMurHash: 784
toString: 3696
BaseFeatureV1: 6232
BaseFeatureV1: 4748
 */