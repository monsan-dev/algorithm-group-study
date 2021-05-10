import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		int[] A = {0,0}; // {cumulative work time, current work time}
		int[] B = {0,0};
		int[] AtoB = {0,0}; // {past move time, current move time}
		int[] BtoA = {0,0};
		int temp, result=0;
		
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		
		for (int i = 0; i < N-1; i++) { // until last's previous input
			
			AtoB[0] = AtoB[1]; // push current to past
			BtoA[0] = BtoA[1];
			
			/* input */
			A[1] = sc.nextInt();
			B[1] = sc.nextInt();
			AtoB[1] = sc.nextInt();
			BtoA[1] = sc.nextInt();

			if (i == 0) { // first loop
				A[0] = A[1]; // push to past
				B[0] = B[1];
				continue; // do not compute
			}
			
			temp = computeMin(A[0],A[1],B[0],BtoA[0]);
			B[0] = computeMin(B[0],B[1],A[0],AtoB[0]);
			A[0] = temp;
		}
		// last loop
		A[1] = sc.nextInt();
		B[1] = sc.nextInt();
		
		temp = computeMin(A[0],A[1],B[0],BtoA[1]);
		B[0] = computeMin(B[0],B[1],A[0],AtoB[1]);
		A[0] = temp;
		
		if(A[0] > B[0])
			result = B[0];
		else
			result = A[0];
		
		System.out.print(result);
		
	}
	public static int computeMin(int X1,int X2,int Y1,int move) {
		/* Calculate the minimum cost up to this point  */
		if (X1 + X2 > Y1 + move + X2) // if moving is economical
			X1 = Y1 + move + X2; // update cumulative cost to this point
		else
			X1 += X2;
		return X1;
	}
}
