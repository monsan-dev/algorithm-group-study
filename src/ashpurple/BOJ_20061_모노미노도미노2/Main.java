import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] green = new boolean[6][4];
	static boolean[][] blue = new boolean[6][4];
	static int score = 0, tileNum = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			int t, x, y;
			st = new StringTokenizer(br.readLine());
			t = stoi(st.nextToken());
			x = stoi(st.nextToken());
			y = stoi(st.nextToken());
			
			/* algorithm */
			// drop
			drop(green, y, t);
			
			if(t == 2)
				t = 3;
			else if(t == 3)
				t = 2;
	
			drop(blue, x, t);
			
			// pop
			popAndMove(green);
			popAndMove(blue);
			
			// special pop
			while(isLight(green)) move(green, 5);
			while(isLight(blue)) move(blue, 5);

		}	
		countTile();
		/* output */
		System.out.println(score);
		System.out.println(tileNum);
	}
	
	private static void drop(boolean[][] board, int y, int t) {
		int floor = 0;
		
		switch(t) {
		case 1: // 1 * 1
			while(!board[floor][y]) {
				floor++;
				if(floor == 6)
					break;
			}
			board[floor - 1][y] = true;
			break;
		case 2: // 1 * 2
			while(!board[floor][y] && !board[floor][y + 1]){
				floor++;
				if(floor == 6)
					break;
			}
			board[floor - 1][y] = true;
			board[floor - 1][y + 1] = true;
			break;
		case 3: // 2 * 1
			while(!board[floor][y]){
				floor++;
				if(floor == 6)
					break;
			}
			board[floor - 1][y] = true;
			board[floor - 2][y] = true;
			break;
		}
		
	}
	
	private static int getFullIndex(boolean[][] board) {
		for(int i = 5; 1 < i; i--) {
			int cnt = 0;
			for(int j = 0; j < 4; j++) {
				if(board[i][j])
					cnt++;
			}
			if(cnt == 4)
				return i; // full index
		}
		return -1; // no full
	}
	
	private static void move(boolean[][] board, int y) {
		for(int i = y; 0 < i; i--) {
			for(int j = 0; j < 4; j++) {
				board[i][j] = board[i - 1][j];
			}
		}
		for(int j = 0; j < 4; j++) { // initialize top floor
			board[0][j] = false;
		}
	}
	
	private static void popAndMove(boolean[][] board) {
		int fullIndex = getFullIndex(board);
		while(fullIndex != -1){ // if full
			move(board, fullIndex);
			fullIndex = getFullIndex(board);
			score++;
		}
	}
	
	private static boolean isLight(boolean[][] board) {
		for(int i = 1; 0 <= i; i--) {
			for(int j = 0; j < 4; j++) {
				if(board[i][j])
					return true;
			}
		}
		return false;
	}
	
	private static void countTile() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				if(green[i][j])
					tileNum++;
				if(blue[i][j])
					tileNum++;
			}
		}
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}

