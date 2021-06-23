#include<cstdio>

int main(void) {
	int N, K;
	scanf("%d %d\n", &N, &K);

	unsigned int* volumeArray = new unsigned int[N];

	for (int i = 0; i < N; ++i) {
		scanf("%d", &volumeArray[i]);
	}

	int maxValue = volumeArray[0];

	for (int i = 1; i < N; ++i) {		//find the biggest value in volume array
		if (volumeArray[i] > maxValue)
			maxValue = volumeArray[i];
	}

	int low = 1, high = maxValue;
	unsigned int result=0;

	while (low <= high) {			//binary search : BigO = log2 N
		int counter = 0;

		int mid = low + high >> 1;	//divide by 2

		for (int i = 0; i < N; ++i) {
			counter += volumeArray[i] / mid;
		}

		if (counter >= K) {
			result = mid;
			low = mid + 1;
		}
		else {
			high = mid - 1;
		}
	}

	delete []volumeArray;

	printf("%d\n", result);
}