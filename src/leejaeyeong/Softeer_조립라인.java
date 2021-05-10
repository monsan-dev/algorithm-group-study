package date0510;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Softeer_조립라인 {
	static int n, ans, lastA, lastB, res = Integer.MAX_VALUE;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = stoi(br.readLine());
		arr = new int[n-1][4];
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				arr[i][j] = stoi(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		lastA = stoi(st.nextToken());
		lastB = stoi(st.nextToken());
		
		simul(0, 0, 0);
		simul(0, 1, 0);
		
		
		System.out.println(res);
		
	}
	private static void simul(int idx, int choice, int time) {
		if (idx == n-1) {
			time += choice == 0 ? lastA : lastB;
			if (res > time) {
				res = time;
			}
			return;
		}
		time += arr[idx][choice];
		simul(idx+1, choice, time);
		simul(idx+1, choice ^ 1, time + arr[idx][choice+2]);
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
