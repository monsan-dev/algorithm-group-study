/* hanabzu */
#include <iostream>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N,L, rest = 0; 
	cin >> N;
	cin >> L;

	int light[100][3];
	
	// read data
	for (int i = 0; i < N; i++) {
		cin >> light[i][0] >> light[i][1] >> light[i][2];	
	}
	
	// rest is a distance between last traffic light and the goal
	rest = L - light[N-1][0];

	// let D as a distance from pre-traffic light
	for (int i = N - 1; i > 0; i--) {
		light[i][0] -= light[i - 1][0];
	}

	int time = 0;
	int statement; // traffic light's statement
	for (int i = 0; i < N; i++) {
		time += light[i][0];
		statement = time % (light[i][1] + light[i][2]);
		if (statement < light[i][1]) { // if red light, plus a waiting time for green light
			time += (light[i][1] - statement);
		}
	}

	// go to the goal
	time += rest;

	cout << time;

	return 0;
}



