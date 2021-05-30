package date0531;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {
	static int N, M;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken()); // 행
		M = stoi(st.nextToken()); // 열
		arr = new int[N][M];
		
		// 입력 처리
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = stoi(st.nextToken());
			}	
		}
		
		st = new StringTokenizer(br.readLine()); // 명령어 종류 입력
		int R = st.countTokens();
		
		for (int i = 0; i < R; i++) {
			int type = stoi(st.nextToken());
			switch (type) {
			case 1:
				reverseTopNBottom(); // 상하반전
				break;
			case 2:
				reverseLeftNRight(); // 좌우반전
				break;
			case 3:
				rotateRight(); // 오른쪽 90도 회전
				break;
			case 4:
				rotateLeft(); // 왼쪽 90도 회전
				break;
			case 5:
				divideRight(); // 부분배열 오른쪽 회전
				break;
			case 6:
				divideLeft(); // 부분배열 왼쪽 회전
				break;
			}
		}
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void reverseTopNBottom() { // 상하반전
		int[] temp = new int[M];
		for (int i = 0; i < N / 2; i++) {
			// 내장함수를 이용한 배열 복사
			// 원본 배열, 시작위치, 복사할 배열, 복사할 배열의 시작위치, 복사할 길이
			System.arraycopy(arr[i], 0, temp, 0, M);
			System.arraycopy(arr[N - i - 1], 0, arr[i], 0, M);
			System.arraycopy(temp, 0, arr[N - i - 1], 0, M);
		}
	}
	
	private static void reverseLeftNRight() { // 좌우반전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][M - j - 1];
				arr[i][M - j - 1] = temp;
			}
		}
	}
	
	private static void rotateRight() { // 오른쪽 90도
		int[][] temp = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[j][i] = arr[N - 1 - i][j];
			}
		}
		arr = temp;
		int T = N;
		N = M;
		M = T;
	}
	
	private static void rotateLeft() { // 왼쪽 90도
		int[][] temp = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[j][i] = arr[i][M - j - 1];
			}
		}
		arr = temp;
		int T = N;
		N = M;
		M = T;
	}
	
	private static void divideRight() { // 부분배열 오른쪽 회전
		int[][] temp = new int[N][M];
		int r = N / 2, c = M / 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < r && j < c) { // 영역 1
					temp[i][j] = arr[r + i][j];
				} else if (i < r && j >= c) { // 영역 2
					temp[i][j] = arr[i][j - c];
				} else if (i >= r && j < c) { // 영역 3
					temp[i][j] = arr[i][j + c];
				} else { // 영역 4
					temp[i][j] = arr[i - r][j];
				}
			}
		}
		arr = temp;
	}

	private static void divideLeft() { // 부분배열 왼쪽회전
		for (int i = 0; i < 3; i++) 
			divideRight();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
