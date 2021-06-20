#include <iostream>

using namespace std;

const int FIRST_PRICE[7] = { 500, 300, 200, 50, 30, 10, 0 };
const int SECOND_PRICE[6] = { 512, 256, 128, 64, 32, 0 };

int first_festival(int rank) {
    if (rank == 0 || rank > 21)
        return 0;

    int i = 0;
    do {
        rank -= ++i;
    } while (rank > 0);

    return FIRST_PRICE[i-1];
}

int second_festival(int rank) {
    if (rank == 0 || rank > 31)
        return 0;

    int i = 0;
    int sub = 1;

    do {
        rank -= sub;
        sub *= 2;
        i++;
    } while (rank > 0);

    return SECOND_PRICE[i - 1];
}

int main(void)
{
    int num, first_rank, second_rank;

    cin >> num;

    for (int i = 0; i < num; ++i) {
        cin >> first_rank >> second_rank;
        cout << (first_festival(first_rank) + second_festival(second_rank)) * 10000 << endl;
    }

    return 0;
}