#include<iostream>
#include<algorithm>
#include<cmath>
#include<vector>

using namespace std;

bool isPrime(int);
bool happyPrime(int);
void splitInt(int);

vector<int> v;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int N;

	cin >> N;

	int testCaseNum;
	int num;

	for (int i = 0; i < N; ++i) {
		cin >> testCaseNum >> num;
		if (isPrime(num) == false) {
			cout << testCaseNum << " " << num << " " << "NO\n";
		}
		else {
			if (happyPrime(num) == true) {
				cout << testCaseNum << " " << num << " " << "YES\n";
			}
			else {
				cout << testCaseNum << " " << num << " " << "NO\n";
			}
		}
	}

}

bool isPrime(int num) {
	if (num < 2) {
		return false;
	}

	for (int i = 2; i <= sqrt(num); ++i) {
		if (num % i == 0) {
			return false;
		}
	}
	return true;
}

bool happyPrime(int num) {
	while (num >= 7) {
		int sum = 0;
		splitInt(num);

		for (int n : v) {
			sum += pow(n, 2);
		}
		num = sum;
	}

	if (num == 1) {
		return true;
	}
	else {
		return false;
	}
}

void splitInt(int num) {
	int n;
	v.clear();
	while (num != 0) {
		n = num % 10;
		num /= 10;
		v.push_back(n);
	}
}