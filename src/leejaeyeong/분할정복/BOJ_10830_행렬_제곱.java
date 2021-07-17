import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10830_행렬_제곱 {
	static int n;
	static long r;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = stoi(st.nextToken());
		r = Long.parseLong(st.nextToken());
		arr = new int[n][n];
		
		// input
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = stoi(st.nextToken()) % 1000;
			}
		}

		arr = divide(arr, r);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int[][] divide(int[][] arr, long r) {
		if (r % 2 == 0) {
			int[][] sub = divide(arr, r / 2);
			return compute(sub, sub);
		} else {
			return (r == 1) ? arr : compute(divide(arr, r - 1), arr);
		}
	}
	private static int[][] compute(int[][] arr1, int[][] arr2) {
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					res[i][j] += arr1[i][k] * arr2[k][j];
				}
				res[i][j] %= 1000;
			}
		}
		return res;
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
