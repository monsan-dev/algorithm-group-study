import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] matrix; // matrix

	public static void main(String[] args) throws Exception {
		int N,M; // matrix row&column
		int num; // the number of computation
		
		/* input */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		num = stoi(st.nextToken());
		
		matrix = new int[N][M];
		int[] compute = new int[num];

		for (int i = 0; i < N; i++) { // create matrix
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				matrix[i][j] = stoi(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < num; i++) {
			compute[i] = stoi(st.nextToken());
			switching(compute[i]); // compute by function
		}
		
		/* output */
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				bw.write(matrix[i][j]+" ");
			}
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		
	}
	
	private static void switching(int number) {
		switch(number) {
		case 1 : func1();
				break;
		case 2 : func2();
				break;
		case 3 : func3();
				break;
		case 4 : func4();
				break;
		case 5 : func5();
				break;
		case 6 : func6();
				break;
		}
	}
	
	private static void func1() {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		for(int col = 0 ; col < colSize ; col++) {
			for(int row1 = 0, row2 = rowSize - 1 ; row1 < row2 ; row1++, row2--) {
				int temp = matrix[row1][col];
				matrix[row1][col] = matrix[row2][col];
				matrix[row2][col] = temp;
			}
		}
	}
	
	private static void func2() {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		for(int row = 0 ; row < rowSize ; row++) {
			for(int col1 = 0, col2 = colSize - 1 ; col1 < col2 ; col1++, col2--) {
				int temp = matrix[row][col1];
				matrix[row][col1] = matrix[row][col2];
				matrix[row][col2] = temp;
			}
		}
	}
	
	private static void func3() {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int[][] newMat = new int[colSize][rowSize];
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[0].length; col++) {
				newMat[col][matrix.length - 1 - row] = matrix[row][col];
			}
		}
		matrix = newMat;
	}
	
	private static void func4() {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int[][] newMat = new int[colSize][rowSize];
		for(int row = 0; row < newMat.length; row++) {
			for(int col = 0; col < newMat[0].length; col++) {
				newMat[row][col] = matrix[col][newMat.length - 1 - row];
			}
		}
		matrix = newMat;
	}
	
	private static void func5() {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int[][] newMat = new int[rowSize][colSize];
		// 1->2
		for(int row = 0 ; row < rowSize / 2 ; row++) {
			for(int col = 0 ; col < colSize / 2 ; col++) {
				newMat[row][col + colSize / 2] = matrix[row][col];
			}
		}
		// 2->3
		for(int row = 0 ; row < rowSize / 2 ; row++) {
			for(int col = colSize / 2 ; col < colSize ; col++) {
				newMat[row + rowSize / 2][col] = matrix[row][col];
			}
		}
		// 3->4
		for(int row = rowSize / 2 ; row < rowSize ; row++) {
			for(int col = colSize / 2 ; col < colSize ; col++) {
				newMat[row][col - colSize / 2] = matrix[row][col];
			}
		}
		// 4->1
		for(int row = rowSize / 2 ; row < rowSize ; row++) {
			for(int col = 0 ; col < colSize / 2 ; col++) {
				newMat[row - rowSize / 2][col] = matrix[row][col];
			}
		}
		matrix = newMat;
	}
	
	private static void func6() {
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int[][] newMat = new int[rowSize][colSize];
		// 1->4
		for(int row = 0 ; row < rowSize / 2 ; row++) {
			for(int col = 0 ; col < colSize / 2 ; col++) {
				newMat[row + rowSize / 2][col] = matrix[row][col];
			}
		}
		// 2->1
		for(int row = 0 ; row < rowSize / 2 ; row++) {
			for(int col = colSize / 2 ; col < colSize ; col++) {
				newMat[row][col - colSize / 2] = matrix[row][col];
			}
		}
		// 3->2
		for(int row = rowSize / 2 ; row < rowSize ; row++) {
			for(int col = colSize / 2 ; col < colSize ; col++) {
				newMat[row - rowSize / 2][col] = matrix[row][col];
			}
		}
		// 4->3
		for(int row = rowSize / 2 ; row < rowSize ; row++) {
			for(int col = 0 ; col < colSize / 2 ; col++) {
				newMat[row][col + colSize / 2] = matrix[row][col];
			}
		}
		matrix = newMat;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
