/* hanabzu */
/* BOJ_10434 행복한 소수 */

#include <iostream>
#include <set>
#include <string>
#include <cmath>

using namespace std;

int sumDigitsSquare(int n);
bool isHappy(int n);
bool isPrime(int n);

set<int> happyNums; // save happy numbers

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int P, N, M;
	string result;

	cin >> P;

	for (int i = 0; i < P; i++) {
		cin >> N >> M;
		result = isHappy(M) && isPrime(M) ? " YES\n" : " NO\n";
		cout << N << ' ' << M << result;
	}
}

int sumDigitsSquare(int n) {
	int sum = 0;
	string str_n = to_string(n);
	for (int i = 0; i < str_n.length(); i++) {
		sum += pow((str_n[i] - '0'), 2);
	}
	return sum;
}

bool isHappy(int n) {
	int sum = n;
	set<int> candidates; // potential numbers

	while (sum != 1) {
		if (happyNums.count(sum)) { // it's already happy!
			break;
		}

		if (candidates.count(sum)) { // loop! it will never be happy...
			return false;
		}

		candidates.insert(sum);
		sum = sumDigitsSquare(sum);
	}

	happyNums.insert(candidates.begin(), candidates.end()); // save it!
	return true;
}

bool isPrime(int n) {
	if (n == 1) {
		return false;
	}
	for (int i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			return false;
		}
	}
	return true;
}