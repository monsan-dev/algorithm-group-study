import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		/* input */
		int N = stoi(br.readLine());
		int wine[] = new int[N];
		int dp[] = new int [N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			wine[i] = stoi(st.nextToken());
		}
		
		/* algorithm */
		dp[0] = wine[0];
		if(2 <= N)
			dp[1] = wine[0] + wine[1];
		
		for(int i = 3; i < N; i++) {
			
			int drink = Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]);
			int no_drink = dp[i - 1];
			
			dp[i] = Math.max(drink, no_drink);
		}
		/* output */
		String result = String.valueOf(dp[N - 1]);
		bw.write(result);
		bw.flush();
		bw.close();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}

