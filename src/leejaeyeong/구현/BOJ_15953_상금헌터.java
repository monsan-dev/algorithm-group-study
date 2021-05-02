import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15953_상금헌터 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] festival2017Money = new int[7][2];
		int[][] festival2018Money = new int[6][2];
		int[] money = { 5000000, 3000000, 2000000, 500000, 300000, 100000 };
		int[] money2 = { 5120000, 2560000, 1280000, 640000, 320000 };
		
		// 우승 상금을 받을 수 있는 인원수, 우승 상금 정보를 초기화
		for (int i = 1; i <= money.length; i++) {
			festival2017Money[i][0] = festival2017Money[i-1][0] + i;  
			festival2017Money[i][1] = money[i-1];
		}
		for (int i = 1; i <= money2.length; i++) {
			festival2018Money[i][0] = festival2018Money[i-1][0] + (int)Math.pow(2, i-1);
			festival2018Money[i][1] = money2[i-1];
		}
		
		int N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int grade2017 = stoi(st.nextToken());
			int grade2018 = stoi(st.nextToken());
			int res = 0;
			if (grade2017 != 0) { // 2017년도에 유의미한 성적을 받은 경우 
				for (int j = 1; j <= money.length; j++) {
					if (festival2017Money[j][0] >= grade2017) { // 상금을 받을 수 있는 인원 범위 안에 들 경우
						res += festival2017Money[j][1];
						break;
					}
				}
			}
			if (grade2018 != 0) { // 2018년도에 유의미한 성적을 받은 경우 
				for (int j = 1; j <= money2.length; j++) {
					if (festival2018Money[j][0] >= grade2018) { // 상금을 받을 수 있는 인원 범위 안에 들 경우
						res += festival2018Money[j][1];
						break;
					}
				}
			}
			System.out.println(res);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
