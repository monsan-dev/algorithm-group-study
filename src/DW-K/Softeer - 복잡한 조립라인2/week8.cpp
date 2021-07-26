#include<iostream>
#include<algorithm>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, K;
	cin >> K >> N;

	int** L = new int* [2];
	L[0] = new int[N + 1]();
	L[1] = new int[N + 1]();

	bool firstIndex = 0;

	for (int i = 0; i < N; ++i) {
		cin >> L[!firstIndex][i];
	}

	for (int i = 1; i < K; ++i) {
		cin >> L[firstIndex][N];

		for (int j = 0; j < N; ++j)
			cin >> L[firstIndex][j];

		int minValue = *min_element(L[firstIndex], L[firstIndex] + N) + L[firstIndex][N];
		for (int j = 0; j < N; ++j) {
			L[firstIndex][j] = minValue < L[firstIndex][j] ? minValue : L[firstIndex][j];
			L[!firstIndex][j] += L[firstIndex][j];
		}

		firstIndex = !firstIndex;
	}

	cout << *min_element(L[firstIndex], L[firstIndex] + N);

	delete[] L[0];
	delete[] L[1];
	delete[] L;

	return 0;
}