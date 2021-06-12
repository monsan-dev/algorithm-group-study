/* hanabzu */
/* Softeer 복잡한 조립라인2 */

#include <iostream>
#include <algorithm>

using namespace std;

int main(int argc, char** argv) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int K, N;

	cin >> K >> N;

	int* work = new int[K];
	int move, next, local_min;

	// read first line
	for (int j = 0; j < K; j++) {
		cin >> work[j];
	}

	// find minimum
	for (int i = 0; i < N - 1; i++) {
		cin >> move;
		local_min = *min_element(work, work + K) + move;
		for (int j = 0; j < K; j++) {
			work[j] = (local_min < work[j]) ? local_min : work[j];
			cin >> next;
			work[j] += next;
		}
	}
	cout << *min_element(work, work + K);
	return 0;
}