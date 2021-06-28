package date0629;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	static int h, n, m, ripe, unripe;
	static int[][][] box;
	static boolean[][][] visited;
	static Queue<int[]> que = new LinkedList<>();
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0, 0, 0};
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = stoi(st.nextToken());
		n = stoi(st.nextToken());
		h = stoi(st.nextToken());
		box = new int[h][n][m];
		visited = new boolean[h][n][m];
		
		// 초기 상자의 상태 input
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < m; k++) {
					box[i][j][k] = stoi(st.nextToken());
					if (box[i][j][k] == 1) { // 익은 토마토 
						ripe++;
						que.offer(new int[] {i, j, k});
						visited[i][j][k] = true;
					} else if(box[i][j][k] == 0) { // 익지 않은 토마토
						unripe++;
					}
				}
			}
		}
		if (unripe == 0) {
			System.out.println(0);
			return;
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		int day = -1, ripeCount = 0;
		while(!que.isEmpty()) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				int z = que.peek()[0];
				int y = que.peek()[1];
				int x = que.poll()[2]; // 익은 토마토의 좌표
				
				for (int k = 0; k < 6; k++) {
					int nz = z + dz[k];
					int ny = y + dy[k];
					int nx = x + dx[k]; // 인접한 위치의 좌표
					
					if (nz < 0 || nz >= h || ny < 0 || ny >= n || nx < 0 || nx >= m) continue; // 박스 영역을 벗어나는 경우
					if (visited[nz][ny][nx] || box[nz][ny][nx] == -1) continue; // 이미 처리했거나, 빈칸인 경우
					
					// 인접한 영역에 토마토가 있고, 익지 않은 경우
					ripeCount++;
					visited[nz][ny][nx] = true;
					que.offer(new int[] {nz, ny, nx});
				}
			}
			day++;
		}
		if (ripeCount != unripe) return -1;
		return day;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
