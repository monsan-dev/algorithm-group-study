import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13702_이상한술집 {
	static int  n, k;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = stoi(st.nextToken()); // 막걸리 수
		k = stoi(st.nextToken()); // 인원 수
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = stoi(br.readLine());
		}
		System.out.println(binarySearch(0, Integer.MAX_VALUE)); // 최적 용량 범위 
	}

	private static int binarySearch(long min, long max) {
		if (min > max) {
			return (int) max; // 최적 용량 반환
		}
		
		long mid = (min + max) / 2;
		if (mid != 0 && solve(mid)) min = mid + 1;
		else max = mid - 1;
		
		return binarySearch(min, max);
	}
	
	private static boolean solve(long amount) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			count += arr[i] / amount; 
			if (count >= k) // k명 이상 나눠줄 수 있는 경우 
				return true;
		}
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
