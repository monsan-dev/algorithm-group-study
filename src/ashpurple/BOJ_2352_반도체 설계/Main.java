import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] lastArr, arr;
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		arr = new int[N];
		lastArr = new int[N]; // dp[i] = smallest last number of i_length LIS
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = stoi(st.nextToken());
		}
		
		int len = 0;
		lastArr[0] = arr[0];
		for(int i = 1; i < N; i++) {
			if(lastArr[len] < arr[i]) 
				lastArr[++len] = arr[i];
			else {
				int idx = lowerBound(arr[i], len);
				lastArr[idx] = arr[i];
			}

		}
		
		/* output */
		String result = String.valueOf(len + 1);
		bw.write(result);
		bw.flush();
		bw.close();
	}
	
	private static int lowerBound(int target, int len) {
	    int begin = 0;
	    int end = len;
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(lastArr[mid] >= target) {
	        	end = mid;
	        }
	        else {
	        	begin = mid + 1;
	        }
	    }
	    return end;
	}
		
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}

