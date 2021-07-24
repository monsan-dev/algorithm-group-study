import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static char[][] board;
	static int minPin, minMove;
	static int curPin, curMove;
	static int row = 5, col = 9;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		
		for(int t = 0; t < N; t++) {
			curPin = 0;
			curMove = 0;
			
			board = new char[row][col];
			for(int i = 0; i < row; i++) {
				String line = br.readLine();
				for(int j = 0; j < col; j++) {
					board[i][j] = line.charAt(j);
					if(board[i][j] == 'o')
						curPin++;
				}
			}
			
			minPin = curPin;
			
			dfs();
		
			/* output */
			bw.write(minPin + " " + minMove + "\n");
			bw.flush();
			
			if(t != N-1)
				br.readLine();
		}
		bw.close();
	}
	
	static boolean isValid(int x1, int y1, int x2, int y2) {
		if(x1 >= 0 && y1 >= 0 && x1 < row && y1 < col) {
			if(board[x1][y1] == 'o') { // has adjacent pin
				if(x2 >= 0 && y2 >= 0 && x2 < row && y2 < col) {
					if(board[x2][y2] == '.') // can jump
						return true;
				}
			}
		}
		return false;
	}
	
	static void dfs(){
		
		for(int x = 0; x < row; x++) {
			for(int y = 0; y < col; y++) {
				
				if(board[x][y] == 'o') {
					
					for(int dir = 0; dir < 4; dir++) {
						int x1 = x + dx[dir]; // move one space
						int y1 = y + dy[dir];
						int x2 = x1 + dx[dir]; // move two space
						int y2 = y1 + dy[dir];
						
						if(isValid(x1, y1, x2, y2)) { // can remove pin
							board[x][y] = '.';
							board[x1][y1] = '.';
							board[x2][y2] = 'o';
							curPin--;
							curMove++;
							
							dfs();
							
							board[x][y] = 'o';
							board[x1][y1] = 'o';
							board[x2][y2] = '.';
							curPin++;
							curMove--;
						}
					}
				}
			}
		}
		
		// update minimum value
		if(curPin < minPin) { 
			minPin = curPin;
			minMove = curMove;
		}
		else if(curPin == minPin && curMove < minMove) {
			minMove = curMove;
		}
    }
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}

