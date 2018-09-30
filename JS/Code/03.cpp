#include <iostream>
#include <algorithm>
#include <limits.h>

using namespace std;

int gcd(int x, int y) {	
	int z = y;
	while(x%y!=0)
	{
		z = x%y;
		x = y;
		y = z;	
	}
	return z;
}

int getMaxSu(int num) {
  for (int i=2; i < num; ++i) {
    if (num % i == 0) {
      return getMaxSu(num / i);
    }
  }
  return num;
}


int main() {
  int n, a, b, as[150000], bs[150000], tgcd = INT_MAX, maxGcd = INT_MAX;
  cin >> n;
  for (int i = 0; i < n; ++i) {
    cin >> as[i] >> bs[i];
    int curMax;
    if (i != 0) {
      curMax = gcd(as[i-1], bs[i]);
      curMax = max(curMax, gcd(as[i-1], bs[i]));
      curMax = max(curMax, gcd(as[i], bs[i-1]));
      curMax = max(curMax, gcd(as[i], bs[i-1]));
    } else {
      continue;
    }
    maxGcd = min(maxGcd, curMax);
  }

  cout << maxGcd << endl;
  cout << getMaxSu(maxGcd) << endl;

  return 0;
}