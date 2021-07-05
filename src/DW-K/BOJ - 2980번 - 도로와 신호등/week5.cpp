#include<iostream>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, L;
	int D, R, G;
	int PD = 0; // pre-D
	int C, RT; // Cycle := (R+G), Rest Time := time % Cycle

	int time = 0;
	cin >> N >> L;

	for (int i = 0; i < N; ++i) {
		cin >> D >> R >> G;

		time += D - PD;
		
		C = R + G;
		RT = time % C;

		if (RT < R || RT > C)
			time += R - RT;
		
		PD = D;
	}
	
	if (D < L)
		time += L - D;

	cout << time;
}