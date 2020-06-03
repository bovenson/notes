#include <iostream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

int cmp(const void *a, const void *b) {
  return *(int *)b - *(int *)a;
}

int main() {
  int n, k, cnt[30];
  memset(cnt, 0, sizeof(int) * 30);
  string cards;
  scanf("%d %d\n", &n, &k);
  cin >> cards;
  for (int i = 0; i < cards.size(); ++i) {
    ++cnt[cards[i] - 'A'];
  }
  qsort(cnt, 30, sizeof(int), cmp);
  int res = 0, pos = 0;
  while (k > 0) {
    if (k >= cnt[pos]) {
      res += cnt[pos] * cnt[pos];
      k -= cnt[pos];
    } else {
      res += k * k;
      k -= k;
    }
  }
  cout << res << endl;
  return 0;
}