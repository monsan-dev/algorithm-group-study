/* hanabzu */
/* BOJ_2352 반도체 설계 */
/* 알고리즘 참조 블로그 : https://shoark7.github.io/programming/algorithm/3-LIS-algorithms#4 */

#include <iostream>
#include <limits.h>
#include <algorithm>

using namespace std;

int N, value, bs, longest = 1;
int c[40001];

int binary_search(int low, int high, int n);

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	
	cin >> N;

	c[0] = INT_MIN;
	fill(c + 1, c + N + 1, INT_MAX);

	for (int i = 1; i <= N; i++) {
		cin >> value;
		if (c[longest] < value) {
			longest++;
			c[longest] = value;
		}
		else {
			bs = binary_search(0, longest, value);
			c[bs] = value;
		}
	}

	cout << longest;

	return 0;
}

int binary_search(int low, int high, int n) {
	if (low == high) {
		return low;
	}
	else if (low + 1 == high) {
		if (c[low] >= n) {
			return low;
		}
		else {
			return high;
		}
	}

	int mid = (low + high) >> 1;

	if (c[mid] == n) {
		return mid;
	}
	else if (c[mid] < n) {
		return binary_search(mid + 1, high, n);
	}
	else {
		return binary_search(low, mid, n);
	}
}