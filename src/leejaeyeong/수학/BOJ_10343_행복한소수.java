import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_10343_행복한소수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();;
		StringTokenizer st;
		
		int P = stoi(br.readLine());
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int tc = stoi(st.nextToken());
			int num = stoi(st.nextToken());
			sb.append(tc + " " + num + " ");
			if (isPrime(num) && isHappyNum(num)) {
				sb.append("YES\n");
				continue;
			}
			sb.append("NO\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean isHappyNum(int num) {
		Set<Integer> numSet = new HashSet<>();
		while(true) {
			int nextNum = compute(num);
			if (nextNum == 1) return true; // 행복한 수인 경우
			if (numSet.contains(nextNum)) return false; // 이미 계산한 수인 경우
			numSet.add(nextNum);
			num = nextNum;
		}
	}
	
	/* 각 자리수의 제곱을 더하는 함수 */
	private static int compute(int num) {
		String strNum = Integer.toString(num);
		int sum = 0;
		for (int i = 0; i < strNum.length(); i++) {
			int n = stoi(Character.toString(strNum.charAt(i))); // 각 자리의 수
			sum += n * n;
		}
		return sum;
	}

	/* 소수 판별 함수 */
	private static boolean isPrime(int num) {
		if (num == 1) 
			return false;
		
		int divisor = (int)Math.sqrt((double)num); // 약수가 될 수 있는 최대 값
		
		for (int i = 2; i <= divisor; i++) {
			if (num % i == 0) { // 나누어 떨어지는 경우 소수 아님
				return false;
			}
		}
		return true;
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
