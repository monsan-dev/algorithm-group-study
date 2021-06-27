def power(h, w):  # 계산하기 쉽게 실수가 나오지않도록 100을 적절히 곱해줬습니다. 어차피 비교만 하면 되는 것이니
    return (h[0]+w[0]) * (100+h[1]+w[1]) * (100*(100-min(h[2]+w[2], 100)) + min(h[2]+w[2], 100) * (h[3]+w[3])) * (100+h[4]+w[4])


cri = list(map(int, input().split()))  # 크리수치
cri_weapon = list(map(int, input().split()))  # 크리의 무기수치
pabu = list(map(int, input().split()))  # 파부수치
pabu_weapon = list(map(int, input().split()))  # 파부의 무기수치

for i in range(5):  # 각각 크리와 파부 차이 비교
    cri[i] -= pabu[i]
    cri_weapon[i] -= pabu_weapon[i]

diff1 = power(cri, pabu_weapon) - power(cri, pabu)  # 크리가 파부의 무기를 장착했을때
diff2 = power(cri_weapon, pabu) - power(cri_weapon, pabu_weapon)  # 파부가 크리의 무기를 장착했을때

if diff1 > 0:
    print('+')
elif diff1 == 0:
    print('0')
else:
    print('-')

if diff2 > 0:
    print('+')
elif diff2 == 0:
    print('0')
else:
    print('-')
