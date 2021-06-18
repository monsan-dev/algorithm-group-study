import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Softeer_복잡한_조립라인2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = stoi(st.nextToken()); // 라인 수 
		int n = stoi(st.nextToken()); // 작업장의 수 
		
		int[][] work = new int[n][k];
		int[] cost = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < k; j++) {
				work[i][j] = stoi(st.nextToken());
			}
			if (i == n - 1) break;
			cost[i] = stoi(st.nextToken());
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < k; j++) {
				int prevVal = Arrays.stream(work[i-1]).min().getAsInt() + cost[i-1];
				work[i][j] += Math.min(prevVal, work[i-1][j]);
			}
		}	
		System.out.println(Arrays.stream(work[n-1]).min().getAsInt());
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}	
}