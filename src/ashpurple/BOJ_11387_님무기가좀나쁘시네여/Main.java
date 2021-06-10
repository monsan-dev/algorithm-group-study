import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		int[][] stat; 
		/* input */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stat=new int[4][5];
		for (int i = 0; i < 4; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			stat[i][0] = stoi(st.nextToken());
			stat[i][1] = stoi(st.nextToken());
			stat[i][2] = stoi(st.nextToken());
			stat[i][3] = stoi(st.nextToken());
			stat[i][4] = stoi(st.nextToken());
		}
		
		for (int i = 0; i < 5; i++) {
			stat[0][i]-=stat[2][i];// player1's original stat
			stat[1][i]-=stat[3][i];// player2's original statÀÌ
		}
	
		/* output */
		
		float player1=computePower(stat[0],stat[3])-computePower(stat[0],stat[2]);
		String result="";
		if(player1>0)
			result="+";
		else if(player1==0)
			result="0";
		else
			result="-";
		System.out.println(result);

		float player2=computePower(stat[1],stat[2])-computePower(stat[1],stat[3]);
		if(player2>0)
			result="+";
		else if(player2==0)
			result="0";
		else
			result="-";
		System.out.println(result);
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	private static float min(float value) {
		if (value>=100)
			value=1;
		else
			value/=100;
		return value;
	}
	
	private static float computePower(int stat[],int weapon[]) {
		float attack=(float)(stat[0]+weapon[0]);
		float strength=(float)(stat[1]+weapon[1]);
		float critical=min((float)(stat[2]+weapon[2]));
		float criticalAttack=(float)(stat[3]+weapon[3])/100;
		float attackSpeed=(float)(stat[4]+weapon[4])/100;
		float power;
		power=attack*(1+(strength/100))*((1-critical)+critical*criticalAttack)*(1+attackSpeed);
		return power;
	}
}
