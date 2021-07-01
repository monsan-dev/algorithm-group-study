import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Tomato{
	int h;
	int n;
	int m;
	
	Tomato(int h, int n, int m){
		this.h = h;
		this.n = n;
		this.m = m;
	}
}

public class Main {
	
	static int M, N, H;
	static int[][][] box;
	static boolean[][][] ripe; // check have got ripe
	static int ripeCnt = 0;
	static int unripeCnt = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		H = stoi(st.nextToken());
		
		LinkedList<Tomato> firstRipe = new LinkedList<>();
		box = new int [H][N][M];
		ripe = new boolean [H][N][M];
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					box[i][j][k] = stoi(st.nextToken());
					if(box[i][j][k] == 1) {
						firstRipe.add(new Tomato(i,j,k)); // add to arraylist
						ripe[i][j][k] = true; 
					}
					else if(box[i][j][k] == 0) {
						unripeCnt++;
						ripe[i][j][k] = false;
					}
				}
			}
		}
		
		/* output */
		String result;
		if(unripeCnt == 0) // all tomatoes ripe already
			result = "0";
		else{ // if unripe tomatoes exist
			
			int day = bfs(firstRipe);
		
			if(ripeCnt != unripeCnt) // there are still unripe tomatoes
				result = "-1";
			else // all tomatoes get ripe
				result = String.valueOf(day);
		}
		bw.write(result);
		bw.flush();
	}
	
	public static int bfs(LinkedList<Tomato> firstRipe) {
		int day = -1;
		int[] posH = {1, -1, 0, 0, 0, 0};
		int[] posN = {0, 0, 1, -1, 0, 0};
		int[] posM = {0, 0, 0, 0, 1, -1};
		
		Queue<Tomato> queue = new LinkedList<>();
		
		/* initial ripe tomatoes */
		for(Tomato tomato : firstRipe) {
			queue.offer(tomato); // push to queue
		}
		
		/* BFS */
		while(!queue.isEmpty()) {
			int cycle = queue.size();
			
			for(int q = 0; q < cycle; q++) {
				Tomato tomato = queue.poll();
				int h = tomato.h;
				int n = tomato.n;
				int m = tomato.m;
				
				for(int i = 0; i < 6; i++) {
					int newH = h + posH[i];
					int newN = n + posN[i];
					int newM = m + posM[i];
					
					if(isValid(newH, newN, newM)) {
						queue.offer(new Tomato(newH, newN, newM)); // push to queue
						ripe[newH][newN][newM] = true; // check visited node
						ripeCnt++; // count tomatoes have changed
					}
				}
			}
			day++;
		}
		return day;
	}
	
	private static boolean isValid(int h, int n, int m){
		
		if(h < 0 || n < 0 || m < 0 || h >= H || n >= N || m >= M) // out of range
			return false;
		else if(ripe[h][n][m]) // already ripe
			return false;
		else if(box[h][n][m] == -1) // box is empty
			return false;
		else
			return true;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}

