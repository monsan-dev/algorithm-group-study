/* hanabzu */
/* BOJ_9207 페그 솔리테어 */

#include <iostream>

using namespace std;

int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };

int N, num_pin, num_moving;
int table[7][11] = { 0, }; // padding
string input;

void solve(int rest, int depth);
bool check(int r, int c, int dir);

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;

	for (int i = 0; i < N; i++) {
		num_pin = 0;
		num_moving = 1000;
		for (int j = 1; j <= 5; j++) {
			cin >> input;
			for (int k = 0; k < input.size(); k++) { // set the table
				if (input[k] == '#') {
					table[j][k + 1] = 0;
				}
				else if (input[k] == '.') {
					table[j][k + 1] = 1;
				}
				else{// input[k] == 'o'
					num_pin++;
					table[j][k + 1] = 2;
				}
			}
		}
		
		solve(num_pin, 0);

		cout << num_pin << ' ' << num_moving << '\n';
	}
	return 0;
}

void solve(int rest, int depth) {
	for (int i = 1; i <= 5; i++) {
		for (int j = 1; j <= 9; j++) {
			if (table[i][j] == 2) { // a pin exists
				for (int dir = 0; dir < 4; dir++) {
					if (check(i, j, dir)) { // moving the pin at the position of table[i][j]
						table[i][j] = 1;
						table[i + dx[dir]][j + dy[dir]] = 1;
						table[i + (dx[dir] << 1)][j + (dy[dir] << 1)] = 2;

						solve(rest - 1, depth + 1); // find next moving

						// back tracking
						table[i][j] = 2;
						table[i + dx[dir]][j + dy[dir]] = 2;
						table[i + (dx[dir] << 1)][j + (dy[dir] << 1)] = 1;
					}
				}
			}
		}
	}

	// game end
	if (rest < num_pin) {
		num_pin = rest;
		num_moving = depth;
	}
	else if (rest == num_pin && depth < num_moving) {
		num_moving = depth;
	}
}

bool check(int r, int c, int dir) {
	r += dx[dir];
	c += dy[dir];
	if (table[r][c] == 2) { // another pin exists on the dir side
		if (table[r + dx[dir]][c + dy[dir]] == 1) { // blank exists
			return true;
		}
	}
	return false;
}
