/* hanabzu */
/* BOJ_2156 포도주 시식 */

#include <iostream>
#include <algorithm>

using namespace std;

int n;
int wine[10000];
int dp[3][10000]; 

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> wine[i];
	}

	if (n < 3) {
		cout << wine[0] + wine[1];
	}
	else {
		dp[0][2] = wine[0] + wine[1];
		dp[1][2] = wine[0] + wine[2];
		dp[2][2] = wine[1] + wine[2];

		for (int i = 3; i < n; i++) {
			dp[0][i] = max({ dp[0][i - 1], dp[1][i - 1], dp[2][i - 1] });
			dp[1][i] = dp[0][i - 1] + wine[i];
			dp[2][i] = dp[1][i - 1] + wine[i];
		}
		cout << max({ dp[0][n - 1], dp[1][n - 1], dp[2][n - 1] });
	}

	return 0;
}