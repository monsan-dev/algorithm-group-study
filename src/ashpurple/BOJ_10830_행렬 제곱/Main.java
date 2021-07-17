import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] matrix;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		long squareNum = stol(st.nextToken());
		
		matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0; j < N; j++) {
				matrix[i][j] = stoi(st.nextToken());
				matrix[i][j] %= 1000;
			}
		}
	
		int[][] result = divideSquareNum(squareNum); // divide & conquer
		
		/* output */
		for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
            	bw.write(result[i][j] + " ");
            }
            bw.write("\n");
        }
		bw.flush();
		bw.close();
	}
	
	static int[][] divideSquareNum(long num){
		int [][] sub;
		if(num == 1)
			return matrix;
		else if(num % 2 == 0){
			sub = divideSquareNum(num / 2);
			return multiply(sub, sub); // square
		}
		else{// if squareNum is odd number
			sub = divideSquareNum(num - 1);
			return multiply(sub, matrix); // multiply
		}
	}
	
	static int[][] multiply(int[][] A, int[][] B){
        int[][] result = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                	result[i][j] += A[i][k] * B[k][j];
                }
                result[i][j] %= 1000;
            }
        }
        return result;
    }
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static long stol(String s) {
		return Long.parseLong(s);
	}

}

