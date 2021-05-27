import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		int num; // the number of traffic light
		int distance; // total distance
		int time=0;
		int curLoc=0; // current location
		
		/* input */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = stoi(st.nextToken());
		distance = stoi(st.nextToken());
		
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int lightLoc = stoi(st.nextToken());
			int R = stoi(st.nextToken());
			int G = stoi(st.nextToken());
			
			time+=lightLoc-curLoc; // time taken to traffic light
			curLoc=lightLoc; // update current location
			
			int cycle=time%(R+G);
			
			if(cycle<=R) // if red light
				time+=R-cycle; // wait until green cycle
		}
		time+=distance-curLoc; // remain distance
		
		/* output */
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(time));
		bw.flush();
		bw.close();
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
