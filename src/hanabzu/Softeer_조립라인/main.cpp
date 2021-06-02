#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int n; // # of input case
	cin >> n;

	int* machine[2];
	int* move[2];
	machine[0] = new int[n]; // A's processing time
	machine[1] = new int[n]; // B's processing time
	move[0] = new int[n-1]; // A to B time
	move[1] = new int[n-1]; // B to A time

	for (int i = 0; i < n; i++) { // read input data
		cin >> machine[0][i];
		cin >> machine[1][i];
		if (i == n - 1)
			break;
		cin >> move[0][i];
		cin >> move[1][i];
	}

	int ans[2] = { machine[0][0],machine[1][0] }; // ans = {A,B}
	int temp[2]; // for independent process
	for (int i = 0; i < n - 1; i++) {
		temp[0] = ans[0];
		temp[1] = ans[1];
		if (temp[0] >= (temp[1] + move[1][i])) { // A line : stay or move
			ans[0] = temp[1] + machine[0][i + 1] + move[1][i];
		}
		else {
			ans[0] +=machine[0][i + 1];
		}

		if (temp[1] >= (temp[0] + move[0][i])) { // B line : stay or move
			ans[1] = temp[0] + machine[1][i + 1] + move[0][i];
		}
		else {
			ans[1] += machine[1][i + 1];
		}
	}

	cout << ((ans[0] < ans[1]) ? ans[0] : ans[1]);

	delete[] machine[0];
	delete[] machine[1];
	delete[] move[0];
	delete[] move[1];
	return 0;
}