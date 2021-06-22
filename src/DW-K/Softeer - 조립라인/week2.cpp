#include<cstdio>

void display(int, int*, int*, int*, int*);
void deleteObject(int*, int*, int*, int*);

int main(void) {
	int N;
	scanf_s("%d\n", &N);

	int* a = new int[N];
	int* b = new int[N];
	int* ab = new int[N-1];
	int* ba = new int[N-1];

	for (int i = 0; i < N - 1; ++i) {
		scanf_s("%d %d %d %d\n", a+i, b+i, ab+i, ba+i);
	}
	scanf_s("%d %d", &a[N - 1], &b[N - 1]);

	if (N == 1) {
		printf("%d", a[0] > b[0] ? b[0] : a[0]);
		deleteObject(a, b, ab, ba);
		return 0;
	}

	for (int i = 1; i < N; ++i) {
		a[i] = b[i - 1] + ba[i - 1] + a[i] > a[i - 1] + a[i] ? a[i - 1] + a[i] : b[i - 1] + ba[i - 1] + a[i];
		b[i] = a[i - 1] + ab[i - 1] + b[i] > b[i - 1] + b[i] ? b[i - 1] + b[i] : a[i - 1] + ab[i - 1] + b[i];
	}

	printf("%d\n", a[N-1] > b[N-1] ? b[N-1] : a[N-1]);

	deleteObject(a, b, ab, ba);

	return 0;
}

void deleteObject(int* a, int* b, int* ab, int* ba) {
	delete[] a;
	delete[] b;
	delete[] ab;
	delete[] ba;
}

void display(int N, int* a, int* b, int* ab, int* ba) {
	printf("----------------\n");
	printf("|  i |  a |  b |\n");
	printf("----------------\n");
	for (int i = 0; i < N; ++i) {
		printf("| %2d ", i + 1);
		printf("| %2d ", a[i]);
		printf("| %2d |\n", b[i]);
	}

	printf("\n\n");
	printf("----------------\n");
	printf("|  i | ab | ba |\n");
	printf("----------------\n");
	for (int i = 0; i < N - 1; ++i) {
		printf("| %2d ", i + 1);
		printf("| %2d ", ab[i]);
		printf("| %2d |\n", ba[i]);
	}
}