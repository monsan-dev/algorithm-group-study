#상금 헌터
import sys

def first(a): #첫번째 코드페스티벌 함수

    prize = 0
    
    if a == 0: #아예 진출을 못했을때
        prize = 0
    if a == 1:
        prize = 500
    if a >= 2 and a <= 3:
        prize = 300
    if a >= 4 and a <= 6:
        prize = 200
    if a >= 7 and a <= 10:
        prize = 50
    if a >= 11 and a <= 15:
        prize = 30
    if a >= 16 and a <= 21:
        prize = 10

    return prize

def second(b): #두번째 코드페스티벌 함수

    prize = 0
    
    if b == 0: #아예 진출을 못했을때
        prize = 0
    if b == 1:
        prize = 512
    if b >= 2 and b <= 3:
        prize = 256
    if b >= 4 and b <= 7:
        prize = 128
    if b >= 8 and b <= 15:
        prize = 64
    if b >= 16 and b <= 31:
        prize = 32
    
    return prize

def main(): #1 첫번째페스티벌과 두번째페스티벌의 상금합
    x, y = map(int, sys.stdin.readline().rstrip().split())
    print((first(x) + second(y)) * 10000)


T = int(input()) # 횟수 입력

for i in range(T): #횟수만큼 반족
    main()
