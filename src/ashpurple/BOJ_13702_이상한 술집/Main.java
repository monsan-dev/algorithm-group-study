import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
		int bottleNum; // the number of bottle
		int peopleNum; // the number of people
		long max = 0; // maximum amount per person
		
		/* input */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bottleNum = stoi(st.nextToken());
		peopleNum = stoi(st.nextToken());
		
		int[] amount=new int[bottleNum]; // amount array

		for (int i = 0; i < bottleNum; i++) {
			amount[i]=stoi(br.readLine());
			
			if(amount[i]>max)
				max=amount[i]; // store maximum amount to max
		}
		
		/* algorithm */
		long result=binarySearch(amount,bottleNum,peopleNum,max);
		bw.write(result+"\n");
		bw.flush();
	}
	
	private static long binarySearch(int[] amount, int bottle, int people, long max) {
		long min=0;
		long mid=0;

		while(max>=min) {
			mid=(max+min)/2; // median
			if(isDivided(amount,bottle,people,mid)) {// if mid value meets the condition
				min=mid+1; // set range to upper
			}
			else {
				max=mid-1; // set range to lower
			}
		}
		return max;
		
	}
	
	private static boolean isDivided(int[] amount,int bottle, int people,long cupAmount) {
		int cnt=0;
		for(int i=0;i<bottle;i++) {
			cnt+=amount[i]/cupAmount; // count the number of cup
			if(cnt>=people) // if the cups are more than people
				return true;
		}
		return false;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
