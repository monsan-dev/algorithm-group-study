
package hanabzu.BOJ_15952_PrizeHunting;
import java.util.Scanner;

class PrizeTable{
    private int[] prize_1 = {5000000,3000000,2000000,500000,300000,100000};
    private int[] prize_2 = {5120000,2560000,1280000,640000,320000};

    public int getPrize_1(int rank){
        if(rank<22 && rank>0){
            for(int i=1;i<7;i++){
                rank-=i;
                if (rank<=0){
                    return prize_1[i-1];
                }
            }
        }
        return 0;
    }
    public int getPrize_2(int rank){
        if(rank<32 && rank>0){
            for(int i=1;i<6;i++){
                rank=rank>>1;
                if(rank<1){
                    return prize_2[i-1];
                }
            }
        }
        return 0;
    }
}

public class main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int prize;
        PrizeTable pt = new PrizeTable();
        for(int i=0; i<n; i++){
            prize= pt.getPrize_1(sc.nextInt())+pt.getPrize_2(sc.nextInt());
            System.out.println(prize);
        }
        sc.close();
    }
}