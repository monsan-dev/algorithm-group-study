/* hanabzu */
#include <iostream>
using namespace std;
class Stat {
private:
	int attack, strength, crit_per, crit_ratio, speed;

public:
	Stat() {
	}

	Stat(int attack, int strength, int crit_per, int crit_ratio, int speed) {
		this->attack = attack;
		this->strength = strength;
		this->crit_per = crit_per;
		this->crit_ratio = crit_ratio;
		this->speed = speed;
	}

	float compute_power() {
		float attack = (float)this->attack;
		float strength = (float)this->strength;
		float crit_per = (float)this->crit_per / 100;
		if (this->crit_per > 100) {
			crit_per = 1;
		}
		float crit_ratio = (float)this->crit_ratio / 100;
		float speed = (float)this->speed / 100;

		return attack * (1 + strength / 100) * ((1 - crit_per) + crit_per * crit_ratio) * (1 + speed);
	}
	
	// operator overloading

	friend Stat operator+(const Stat& A, const Stat& B) {
		Stat C;
		C.attack = A.attack + B.attack;
		C.strength = A.strength + B.strength;
		C.crit_per = A.crit_per + B.crit_per;
		C.crit_ratio = A.crit_ratio + B.crit_ratio;
		C.speed = A.speed + B.speed;
		return C;
	}

	friend Stat operator-(const Stat& A, const Stat& B) {
		Stat C;
		C.attack = A.attack - B.attack;
		C.strength = A.strength - B.strength;
		C.crit_per = A.crit_per - B.crit_per;
		C.crit_ratio = A.crit_ratio - B.crit_ratio;
		C.speed = A.speed - B.speed;
		return C;
	}

};

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int attack, strength, crit_per, crit_ratio, speed;

	// read inputs
	cin >> attack >> strength >> crit_per >> crit_ratio >> speed;
	Stat Cri(attack, strength, crit_per, crit_ratio, speed);

	cin >> attack >> strength >> crit_per >> crit_ratio >> speed;
	Stat Fav(attack, strength, crit_per, crit_ratio, speed);
	
	cin >> attack >> strength >> crit_per >> crit_ratio >> speed;
	Stat weapon_Cri(attack, strength, crit_per, crit_ratio, speed);

	cin >> attack >> strength >> crit_per >> crit_ratio >> speed;
	Stat weapon_Fav(attack, strength, crit_per, crit_ratio, speed);

	// compute differences of weapon changing
	float differ_Cri = Cri.compute_power() - (Cri - weapon_Cri + weapon_Fav).compute_power();
	float differ_Fav = Fav.compute_power() - (Fav - weapon_Fav + weapon_Cri).compute_power();

	if (differ_Cri > 0)
		cout << '-' << endl;
	else if (differ_Cri == 0)
		cout << '0' << endl;
	else
		cout << '+' << endl;
	
	if (differ_Fav > 0)
		cout << '-';
	else if (differ_Fav == 0)
		cout << '0';
	else
		cout << '+';
	return 0;
}



