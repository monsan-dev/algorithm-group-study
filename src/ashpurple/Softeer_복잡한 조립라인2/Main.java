import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int lineNum = stoi(st.nextToken()); // the number of line
		int workNum = stoi(st.nextToken()); // the number of work place
		
		int[] workTime = new int[lineNum];
		int moveTime;
		int previousMin=0;
		Arrays.fill(workTime, 0); // initialize workTime
		
		/* input */
		for(int i = 0; i < workNum-1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < lineNum; j++) {
				workTime[j] = min(previousMin, workTime[j]);
				workTime[j] += stoi(st.nextToken()); // update workTime
			}
			moveTime = stoi(st.nextToken());
			previousMin = min(workTime)+moveTime; // previous minimum workTime
		}
		st = new StringTokenizer(br.readLine()); // last line
		for (int j = 0; j < lineNum; j++) {
			workTime[j] = min(previousMin, workTime[j]);
			workTime[j] += stoi(st.nextToken());
		}
		/* output */
		String result=String.valueOf(min(workTime));
		bw.write(result);
		bw.flush();
		bw.close();
	}
	public static int min(int n[]) {
	    int min = n[0];
	    for (int i = 1; i < n.length; i++)
	      if (n[i] < min) 
	    	  min = n[i];
	    return min;
	}
	private static int min(int X, int Y) {
		if(X > Y)
			return Y;
		else
			return X;
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
}
