import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_9207_페그_솔리테어 {
	static int minCount, minMove;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n  = stoi(br.readLine());
		for (int t = 0; t < n; t++) {
			char[][] arr = new char[5][9];
			List<int[]> candidate = new ArrayList<>();
			minCount = Integer.MAX_VALUE;
			minMove = Integer.MAX_VALUE;
			for (int i = 0; i < 5; i++) {
				String s = br.readLine();
				for (int j = 0; j < 9; j++) {
					arr[i][j] = s.charAt(j);
					if (arr[i][j] == 'o') {
						candidate.add(new int[] {i, j});
					}
				}
			}
			br.readLine();
			char[][] copy = copyArr(arr);
			for (int i = 0; i < candidate.size(); i++) {
				int y = candidate.get(i)[0];
				int x = candidate.get(i)[1];
				dfs(y, x, copy, candidate.size(), 0);
			}
			System.out.println(minCount + " " + minMove);
		}
		
		
	}
	private static void dfs(int y, int x, char[][] arr, int count, int move) {
		if (count < minCount) {
			minCount = count;
			minMove = move;
		} else if (count == minCount) {
			minMove = Math.min(move, minMove);
		}
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i], nx = x + dx[i]; 
			int ty = y + dy[i] * 2, tx = x + dx[i] * 2; 
			
			if (ny < 0 || ny >= 5 || nx < 0 || nx >= 9) continue;
			if (ty < 0 || ty >= 5 || tx < 0 || tx >= 9) continue;
			
			if (arr[ny][nx] == 'o' && arr[ty][tx] == '.') {
				arr[y][x] = arr[ny][nx] = '.';
				arr[ty][tx] = 'o';
				char[][] copy = copyArr(arr);
				for (int j = 0; j < 5; j++) {
					for (int k = 0; k < 9; k++) {
						if (arr[j][k] == 'o') {
							dfs(j, k, copy, count - 1, move + 1);
						}
					}
				}
				arr[y][x] = arr[ny][nx] = 'o';
				arr[ty][tx] = '.';
			}
		}
		
	}
	private static char[][] copyArr(char[][] arr) {
		char[][] copy = new char[5][9];
		for (int i = 0; i < 5; i++) {
			System.arraycopy(arr[i], 0, copy[i], 0, 9);
		}
		return copy;
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
