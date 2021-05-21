/* hanabzu */

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef vector<vector<int>> Matrix; // 2-dimension vector
typedef vector<vector<int>>* MatrixPointer;
MatrixPointer v;

void t1();
void t2();
void t3();
void t4();
void t5();
void t6();
void(*tp[6])() = { t1,t2,t3,t4,t5,t6 }; // function pointer
void printMatrix();

int n, m, r, num, transition;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> n >> m >> r;
	
	v = new Matrix[4];
	for (int i = 0; i < 4; i++) { // make 4 matrices 
		Matrix a;
		v[i] = a;
		for (int j = 0; j < n / 2; j++) { // make vectors and push to matrices
			vector<int> t;
			v[i].push_back(t);
		}
	}
	
	for (int i = 0; i < n; i++) { // read datas
		for (int j = 0; j < m; j++) {
			cin >> num;
			v[2 * (i / (n / 2)) + j / (m / 2)][i % (n / 2)].push_back(num);
		}
	}

	for (int i = 0; i < r; i++) { // transitions
		cin >> transition;
		tp[transition-1]();
	}

	printMatrix();
	return 0;
}

void t1() {
	for (int i = 0; i < 4; i++) {
		reverse(v[i].begin(), v[i].end());
	}
	Matrix temp = v[0];
	v[0] = v[2];
	v[2] = temp;
	temp = v[1];
	v[1] = v[3];
	v[3] = temp;
}

void t2() {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < v[i].size(); j++) {
			reverse(v[i][j].begin(), v[i][j].end());
		}
	}
	Matrix temp = v[0];
	v[0] = v[1];
	v[1] = temp;
	temp = v[2];
	v[2] = v[3];
	v[3] = temp;
}

void t3() {
	for (int i = 0; i < 4; i++) {
		Matrix new_v; 
		for (int j = 0; j < m / 2; j++) {
			vector<int> t;
			new_v.push_back(t);
		}
		for (int j = v[i].size()-1; j >= 0 ; j--) {
			for (int k = 0; k < v[i][j].size(); k++) {
				new_v[k].push_back(v[i][j][k]);
			}
		}
		v[i] = new_v;
	}
	t5();

	int t = n; // swap row & column
	n = m;
	m = t;
}

void t4() {
	for (int i = 0; i < 4; i++) {
		Matrix new_v;
		for (int j = 0; j < m / 2; j++) {
			vector<int> t;
			new_v.push_back(t);
		}
		for (int j = 0; j < v[i].size(); j++) {
			for (int k = 0; k < v[i][j].size(); k++) {
				new_v[v[i][j].size()-k-1].push_back(v[i][j][k]);
			}
		}
		v[i] = new_v;
	}
	t6();

	int t = n; // swap row & column
	n = m;
	m = t;
}

void t5() {
	Matrix temp = v[0];
	v[0] = v[2];
	v[2] = v[3];
	v[3] = v[1];
	v[1] = temp;
}

void t6() {
	Matrix temp = v[0];
	v[0] = v[1];
	v[1] = v[3];
	v[3] = v[2];
	v[2] = temp;
}

void printMatrix() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < v[2 * (i / (n / 2))][i % (n / 2)].size() ;j++){
			cout << v[2 * (i / (n / 2))][i % (n / 2)][j] << " ";
		}
		for (int j = 0; j < v[2 * (i / (n / 2))+1][i % (n / 2)].size(); j++) {
			cout << v[2 * (i / (n / 2))+1][i % (n / 2)][j] << " ";
		}
		cout << "\n";
	}
}
