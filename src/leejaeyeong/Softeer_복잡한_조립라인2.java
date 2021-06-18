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
		int[] work = new int[k];
		
		// 시작 작업장 input
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			work[i] = stoi(st.nextToken());
		}
		int moveCost = stoi(st.nextToken());
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int prevVal = Arrays.stream(work).min().getAsInt() + moveCost; // 이전 작업장까지의 최소 비용 + 이동비용
			for (int j = 0; j < k; j++) {
				work[j] = Math.min(prevVal, work[j]) + stoi(st.nextToken());
			}
			if (i == n - 1) break;
			moveCost = stoi(st.nextToken());
		}
		
		System.out.println(Arrays.stream(work).min().getAsInt());
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}	
}
