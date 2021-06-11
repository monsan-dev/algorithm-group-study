import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) throws Exception {
		int testCase;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		testCase=stoi(br.readLine());
		
		for(int i = 0; i < testCase; i++) {
			/* input */
			StringTokenizer st = new StringTokenizer(br.readLine());
			int testNum = stoi(st.nextToken());
			int number = stoi(st.nextToken());
			String result=testNum + " "+number;
			
			if(isPrime(number))
				if(isHappy(number))
					result +=" YES";
				else
					result += " NO";
			else
				result += " NO";
			/* output */
			bw.write(result+="\n");
			bw.flush();
		}
		bw.close();
	}
	
	private static boolean isPrime(int num) {
		if(num == 1)
			return false;
		else if(num == 2)
			return true;
		else if (num % 2 == 0)// if even number
			return false;
		
		else {
			for(int i = 3; i <= Math.sqrt(num); i+=2) {
				if(num % i == 0)
					return false;
			}
			return true;
		}
	}
	
	private static boolean isHappy(int num) {
		HashSet<Integer> sumSet = new HashSet<Integer>();// dp set
		int sum = 0;// Sum of squared digits
		
		while(sum != 1) {// if happy status, terminates
			sum = 0;
			while(num != 0) {// digit computation
				int digit = num % 10;
				sum += digit * digit;
				num /= 10;
			}
			if (sumSet.contains(sum))// if loop status
				return false;
			sumSet.add(sum); // memoization
			num = sum;// update for next cycle
		}
		return true;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
}
