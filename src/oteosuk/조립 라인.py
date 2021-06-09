import sys

a_sel = 0
b_sel = 0
a_sel_temp = 0
final_sel = 0 #최적값 변수

aList = []
bList = []
A_to_B_List = []
B_to_A_List = []

def choice(x,y): #작거나 같은값을 출력하는 함수
    if x <= y:
        return x
    else:
        return y


N = int(input()) # 횟수 입력

if N == 1:
    aN, bN = map(int, sys.stdin.readline().rstrip().split())
    print(choice(aN, bN))

elif N == 2:
        a, b, a_to_b, b_to_a = map(int, sys.stdin.readline().rstrip().split())
        aN, bN = map(int, sys.stdin.readline().rstrip().split())
        a_sel = choice(a + aN ,  b + aN + b_to_a)  # A공장에 도착하는 두가지 경우, 즉 a(i) -> a(i+1) 와 b(i) -> a(i+1) 둘중 작거나 같은 값 반환
        b_sel = choice(b + bN ,  a + bN + a_to_b)  # B공장에 도착하는 두가지 경우, 즉 b(i) -> b(i+1) 와 a(i) -> b(i+1) 둘중 작거나 같은 값 반환
        final_sel = choice(a_sel, b_sel)

        print(final_sel)
elif N > 2:
    for x in range(N-1): #횟수만큼 반족
        a, b, a_to_b, b_to_a = map(int, sys.stdin.readline().rstrip().split())
        
        aList.append(a)
        bList.append(b)
        A_to_B_List.append(a_to_b)
        B_to_A_List.append(b_to_a)

    aN, bN = map(int, sys.stdin.readline().rstrip().split())

    a_sel = choice( aList[0] + aList[1] , bList[0] + aList[1] + B_to_A_List[0] )
    b_sel = choice( bList[0] + bList[1] , aList[0] + bList[1] + A_to_B_List[0] )
    
    
    for i in range(1,N-1):
        if i < N-2:
            a_sel_temp = choice( a_sel + aList[i+1] , b_sel + aList[i+1] + B_to_A_List[i] )
            b_sel = choice( b_sel + bList[i+1] , a_sel + bList[i+1] + A_to_B_List[i] )
            a_sel = a_sel_temp
        else:
            a_sel_temp = choice(a_sel + aN, b_sel + aN + B_to_A_List[N-2])
            b_sel = choice(b_sel + bN, a_sel + bN + A_to_B_List[N-2])
            a_sel = a_sel_temp
    final_sel = choice(a_sel, b_sel)
    print(final_sel)
    