#include<iostream>

using namespace std;

void deleteObject(int**, int);
void swap(int*, int*);
void firstCal(int**, int);
void secondCal(int**, int, int);
int** thirdCal(int**, int, int);
int** fourthCal(int**, int, int);
int** fifthCal(int**, int, int);
int** sixthCal(int**, int, int);
void displayMat(int**, int, int);

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, M, R;

	cin >> N >> M >> R;

	int** mat = new int* [N];

	for (int i = 0; i < N; ++i) {
		mat[i] = new int[M];
		for (int j = 0; j < M; ++j) {
			mat[i][j] = 0;
		}
	}

	int* calOrder = new int[R];

	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			cin >> mat[i][j];
		}
	}

	for (int i = 0; i < R; ++i) {
		cin >> calOrder[i];
	}

	int order;
	int temp;
	for (int i = 0; i < R; ++i) {
		order = calOrder[i];

		switch (order)
		{
		case 1:
			firstCal(mat, N);
			break;
		case 2:
			secondCal(mat, N, M);
			break;
		case 3:
			mat = thirdCal(mat, N, M);
			swap(&N, &M);
			break;
		case 4:
			mat = fourthCal(mat, N, M);
			swap(&N, &M);
			break;
		case 5:
			mat = fifthCal(mat, N, M);
			break;
		case 6:
			mat = sixthCal(mat, N, M);
			break;
		default:
			break;
		}
	}

	displayMat(mat, N, M);

	deleteObject(mat, N);

	return 0;
}

void firstCal(int** mat, int N) {
	int* tempPtr;

	for (int i = 0; i < N/2; ++i) {
		tempPtr = mat[i];
		mat[i] = mat[N - 1 - i];
		mat[N - 1 - i] = tempPtr;
	}
}

void secondCal(int** mat, int N, int M) {
	int tempValue;

	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M/2; ++j) {
			tempValue = mat[i][j];
			mat[i][j] = mat[i][M - 1 - j];
			mat[i][M - 1 - j] = tempValue;
		}
	}
}

int** thirdCal(int** mat, int N, int M) {
	int** tempMat = new int* [M];		//make temp matrix
	for (int i = 0; i < M; ++i) {
		tempMat[i] = new int[N];
	}

	for (int i = 0; i < M; ++i) {
		for (int j = 0; j < N; ++j) {
			tempMat[i][j] = mat[N - 1 - j][i];
		}
	}

	deleteObject(mat, N);

	return tempMat;
}

int** fourthCal(int** mat, int N, int M) {
	int** tempMat = new int* [M];		//make temp matrix
	for (int i = 0; i < M; ++i) {
		tempMat[i] = new int[N];
	}

	for (int i = 0; i < M; ++i) {
		for (int j = 0; j < N; ++j) {
			tempMat[i][j] = mat[j][M-1-i];
		}
	}

	deleteObject(mat, N);

	return tempMat;
}

int** fifthCal(int** mat, int N, int M) {
	int** tempMat = new int* [N];		//make temp matrix
	for (int i = 0; i < N; ++i) {
		tempMat[i] = new int[M];
	}

	for (int i = 0; i < N / 2; ++i) {		//Quadrant 1 to Quadrant 4
		for (int j = 0; j < M / 2; ++j) {
			tempMat[i][j] = mat[i + N / 2][j];
		}
	}

	for (int i = 0; i < N / 2; ++i) {		//Quadrant 2 to Quadrant 1
		for (int j = M / 2; j < M; ++j) {
			tempMat[i][j] = mat[i][j - M / 2];
		}
	}

	for (int i = N / 2; i < N; ++i) {		//Quadrant 3 to Quadrant 2
		for (int j = M / 2; j < M; ++j) {
			tempMat[i][j] = mat[i - N / 2][j];
		}
	}

	for (int i = N / 2; i < N; ++i) {	//Quadrant 4 to Quadrant 3
		for (int j = 0; j < M / 2; ++j) {
			tempMat[i][j] = mat[i][j + M / 2];
		}
	}

	deleteObject(mat, N);

	return tempMat;
}

int** sixthCal(int** mat, int N, int M) {
	int** tempMat = new int* [N];		//make temp matrix
	for (int i = 0; i < N; ++i) {
		tempMat[i] = new int[M];
	}

	for (int i = 0; i < N/2; ++i) {		//Quadrant 1 to Quadrant 2
		for (int j = 0; j < M/2; ++j) {
			tempMat[i][j] = mat[i][j+M/2];
		}
	}

	for (int i = 0; i < N/2; ++i) {		//Quadrant 2 to Quadrant 3
		for (int j = M / 2; j < M; ++j) {
			tempMat[i][j] = mat[i + N/2][j];
		}
	}

	for (int i = N/2; i < N; ++i) {		//Quadrant 3 to Quadrant 4
		for (int j = M/2; j < M; ++j) {
			tempMat[i][j] = mat[i][j - M/2];
		}
	}

	for (int i = N / 2; i < N; ++i) {	//Quadrant 4 to Quadrant 1
		for (int j = 0; j < M / 2; ++j) {
			tempMat[i][j] = mat[i - N / 2][j];
		}
	}

	deleteObject(mat, N);

	return tempMat;
}

void deleteObject(int** mat, int N) {
	for (int i = 0; i < N; ++i) {
		delete[]mat[i];
	}
	delete[]mat;
}

void swap(int* a, int* b) {
	int temp = *a;
	*a = *b;
	*b = temp;
}

void displayMat(int** mat, int N, int M) {
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			cout << mat[i][j] << " ";
		}
		cout << "\n";
	}
}