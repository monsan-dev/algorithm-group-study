# 메인 아이디어 = 이중리스트를 이용한 배열 구현

import copy  # 딥카피를 사용하기 위함

n, m, r_count = map(int, input().split())  # n x m 배열, r_count 은 연산수

# ud_list = []  필요없어서 삭제
# lr_list = []  전역변수로 안해도 될것 같아서 삭제
# lr_temp_list = []  필요없어서 삭제

my_list = []  # 메인 리스트
case_list = []  # 몇번 연산을 수행할지 결정하게 해주는 리스트


for _ in range(n):
    my_list.append(list(map(int, input().split())))  # 이중리스트로 배열 입력 받음

case_list = list(map(int, input().split()))  # 몇번 연산을 수행할지 리스트로 입력 받음


def num1():  # 상하반전연산
    """
        # 밑에 코드로 상하반전 할라 했으나 my_list.reverse() 로 간단하게 해결 할 수 있어서 삭제

        x = 1
        while x <= n:
            ud_list.append(my_list[n - x])
            x = x + 1
        """
    my_list.reverse()  # 상하반전


def num2():  # 좌우반전연산
    global my_list  # my_list 를 전역 변수로 취급해달라는 의미(파이썬이라 어쩔수없음, 선언필요)

    lr_list = []  # 임시로 빈 리스트 생성

    for row in range(n):
        lr_list.append(my_list[row][::-1])  # 좌우반전(슬라이싱 이용해서)

    my_list = copy.deepcopy(lr_list)  # 메인리스트로 치환

    """
    for i in range(n):
        my_list[i] = lr_list[i]  # 메인 리스트 치환을 이 방법을 사용해도 되나, 밑에 딥카피를 이용해야만 하는 부분이 있어
                                   통일성을 위해 삭제, 그래서 여기서도 딥카피 사용
    
    
    
    # 좌우반전을 밑에 코드를 사용할라 했으나 참조 문제로 인한 삭제 ( deepcopy 로 대체함)
    x = 0
    y = 1
    while x < n:
        while y <= m:
            lr_temp_list.append(my_list[x][m - y]) 
            y = y + 1
        lr_list.append(lr_temp_list)
        y = 1
        x = x + 1
        del lr_temp_list[:]        # 이 부분에서 문제가 발생함
    """


def num3():  # 오른쪽 90도 회전 연산
    global my_list
    global n, m

    blank_list = [[0] * len(my_list) for _ in range(len(my_list[0]))]
    # 0으로 채워진 빈 이중리스트를 만드는 것, 다만 90도 회전이기 때문에 n x m 에서 m x n 으로 바뀜

    for r in range(len(my_list)):
        for c in range(len(my_list[0])):
            blank_list[c][len(my_list) - 1 - r] = my_list[r][c]  # 오른쪽으로 90도 회전(빈 리스트에 일일이 대입)

    my_list = copy.deepcopy(blank_list)

    n, m = m, n  # 회전하면서 m x n 이 n x m 으로 바뀐다.

    """
    list(zip(*my_list[::-1]))      # [::-1]를 이용하여 my_list 를 리버스 시킨 후(상하반전) 병렬입력(zip)
    
    위에 코드를 사용할라 했으나 zip 메소드가 원소들을 튜플로 받아버려서 문제가 발생하여 삭제 
    """


def num4():  # 왼쪽으로 90도 회전 연산 ( num3()과 마찬가지 )
    global my_list
    global n, m

    blank_list = [[0] * len(my_list) for _ in range(len(my_list[0]))]

    for r in range(len(my_list)):
        for c in range(len(my_list[0])):
            blank_list[c][r] = my_list[r][len(my_list[0]) - 1 - c]

    my_list = copy.deepcopy(blank_list)

    n, m = m, n


def num5():  # 시계방향 그룹 이동 연산
    global my_list

    blank_list = [[0] * len(my_list[0]) for _ in range(len(my_list))]

    for r in range(int(n / 2)):
        for c in range(int(m / 2)):
            blank_list[r][int(m / 2) + c] = my_list[r][c]  # 1번그룹을 2번그룹으로
            blank_list[int(n / 2) + r][int(m / 2) + c] = my_list[r][int(m / 2) + c]  # 2번그룹을 3번그룹으로
            blank_list[int(n / 2) + r][c] = my_list[int(n / 2) + r][int(m / 2) + c]  # 3번그룹을 4번그룹으로
            blank_list[r][c] = my_list[int(n / 2) + r][c]  # 4번그룹을 1번그룹으로

    my_list = copy.deepcopy(blank_list)


def num6():  # 반시계방향 그룹 이동 연산
    global my_list

    blank_list = [[0] * len(my_list[0]) for _ in range(len(my_list))]

    for r in range(int(n / 2)):
        for c in range(int(m / 2)):
            blank_list[int(n / 2) + r][c] = my_list[r][c]  # 1번그룹을 4번그룹으로
            blank_list[int(n / 2) + r][int(m / 2) + c] = my_list[int(n / 2) + r][c]  # 4번그룹을 3번그룹으로
            blank_list[r][int(m / 2) + c] = my_list[int(n / 2) + r][int(m / 2) + c]  # 3번그룹을 2번그룹으로
            blank_list[r][c] = my_list[r][int(m / 2) + c]  # 2번그룹을 1번그룹으로

    my_list = copy.deepcopy(blank_list)


def choice(num):  # case 리스트를 이용하여 몇번 연산을 수행하게 할지 결정하게 해주는 함수
    if num == 1:
        num1()
    elif num == 2:
        num2()
    elif num == 3:
        num3()
    elif num == 4:
        num4()
    elif num == 5:
        num5()
    elif num == 6:
        num6()


for i in range(r_count):  # 연산 수 r 만큼 연산 실행
    choice(case_list[i])

for i in my_list:  # a에서 안쪽 리스트를 꺼냄
    for j in i:  # 안쪽 리스트에서 요소를 하나씩 꺼냄
        print(j, end=' ')  # 띄어쓰기
    print()  # 다음줄 넘어가기
