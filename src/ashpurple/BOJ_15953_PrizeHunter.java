import java.util.Scanner;

public class BOJ_15953_PrizeHunter {

	public static void main(String[] args) {
		// [본선진출x,1st,2nd,3rd..]의 상금
		int[] firstPrize= {0,5000000,3000000,2000000,500000,300000,100000};
		int[] secondPrize= {0,5120000,2560000,1280000,640000,320000};
		
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		
		for (int testcase=0; testcase<T; testcase++) {
			int a=sc.nextInt();//첫번째 등수
			int b=sc.nextInt();//두번째 등수
			
			int firstRank=0,secondRank=0; // 해당 랭크
		
			while(a>0 && a<=21) { // 본선 진출했고 21등 이하일때
				firstRank++;
				a-=firstRank;
			}
			while(b>0 && b<=31) { // 본선 진출했고 31등 이하일때
				b/=2;
				secondRank++;
			}
			System.out.println(firstPrize[firstRank]+secondPrize[secondRank]);
		}
	}
}
