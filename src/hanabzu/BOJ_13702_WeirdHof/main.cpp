/* hanabzu */
#include <iostream>
#include <vector>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, k, p = 0; // p is # of people who can receive makgeolli
	cin >> n;
	cin >> k;

	vector<unsigned int> v(n); // volumes of makgeolli
	unsigned int t; // a temp volume for read input
	unsigned int max = 0;

	/* read volumes */
	for (int i = 0; i < n; i++) {
		cin >> t;
		v[i] = t;
		max = (max < v[i]) ? v[i] : max;
	}

	/* find upper bound */
	unsigned int low = 0;
	unsigned int high = max + 1; // !! IDKW must do +1 to a maximum volume !!
	unsigned int mid = 0;

	while (low < high) {
		p = 0;
		mid = (high + low) >> 1;
		if (mid != 0) {
			for (int i = 0; i < n; i++) {
				p += v[i] / mid; // count # of people who can receive
			}
		}
		if (p >= k) {
			low = mid + 1;
		}
		else {
			high = mid;
		}
	}

	/* answer is upper bound minus 1, so as low minus 1 */
	cout << (low == 0 ? low : --low); //prevent underflow
	return 0;
}



