import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2980_도로와_신호등 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = stoi(st.nextToken());  // 신호등 개수
		int L = stoi(st.nextToken()); // 도로 길이
		int currLoc = 0, time = 0; // 최근위치, 소모 시간
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int loc = stoi(st.nextToken());
			int red = stoi(st.nextToken());
			int green = stoi(st.nextToken());
			
			time += loc - currLoc;
			currLoc = loc;
			
			int stop = time % (red + green); 
			if (stop < red) { // 빨간불에서 정지하는 시간
				time += red - stop;
			}
		}
		System.out.println(time + L - currLoc);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}