N, K = map(int, input().split())
pot_list = [int(input()) for _ in range(N)]
low, high = 1, max(pot_list)
x_ml = 0
while low <= high:
    m = (low + high) // 2
    total = sum(n//m for n in pot_list)
    if total >= K:
        x_ml = m
        low = m + 1
    else:
        high = m - 1
print(x_ml)
