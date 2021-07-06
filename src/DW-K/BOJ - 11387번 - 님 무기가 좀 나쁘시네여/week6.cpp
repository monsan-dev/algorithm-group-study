#include<iostream>
#include<algorithm>
using namespace std;

inline float getPower(float, float, float, float, float);

class User {
private:
	float fighting_power;
	float attack_power;
	float strength;
	float fatal_hit_prob;
	float fatal_hit_damage_ratio;
	float add_attack_rate;

	inline float setPower() {
		return attack_power * (1 + this->strength / (float)100.0) * (((float)1.0 - min(this->fatal_hit_prob, (float)1.0)) + min(this->fatal_hit_prob, (float)1.0) * this->fatal_hit_damage_ratio) * ((float)1.0 + this->add_attack_rate);
	}

public:
	User() {
		this->attack_power = 0;
		this->strength = 0;
		this->fatal_hit_prob = 0;
		this->fatal_hit_damage_ratio = 0;
		this->add_attack_rate = 0;
		this->fighting_power = 0;
	}
	User(float new_attack_power, float new_strength, float new_fatal_hit_prob, float new_fatal_hit_damage_ratio, float new_add_attack_rate) {
		this->attack_power = new_attack_power;
		this->strength = new_strength;
		this->fatal_hit_prob = new_fatal_hit_prob;
		this->fatal_hit_damage_ratio = new_fatal_hit_damage_ratio;
		this->add_attack_rate = new_add_attack_rate;
		this->fighting_power = setPower();
	}
	~User() {}

	void release_equip(float equip_attack_power, float equip_strength, float equip_fatal_hit_prob, float equip_fatal_hit_damage_ratio, float equip_add_attack_rate) {
		this->attack_power -= equip_attack_power;
		this->strength -= equip_strength;
		this->fatal_hit_prob -= equip_fatal_hit_prob;
		this->fatal_hit_damage_ratio -= equip_fatal_hit_damage_ratio;
		this->add_attack_rate -= equip_add_attack_rate;
		this->fighting_power = setPower();
	}

	void equip(float equip_attack_power, float equip_strength, float equip_fatal_hit_prob, float equip_fatal_hit_damage_ratio, float equip_add_attack_rate) {
		this->attack_power += equip_attack_power;
		this->strength += equip_strength;
		this->fatal_hit_prob += equip_fatal_hit_prob;
		this->fatal_hit_damage_ratio += equip_fatal_hit_damage_ratio;
		this->add_attack_rate += equip_add_attack_rate;
		this->fighting_power = setPower();
	}

	inline float getPower() {
		return this->fighting_power;
	}
};

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	float attack_power[4], strength[4], fatal_hit_prob[4], fatal_hit_damage_ratio[4], add_attack_rate[4];

	for (int i = 0; i < 4; ++i) {
		cin >> attack_power[i] >> strength[i] >> fatal_hit_prob[i] >> fatal_hit_damage_ratio[i] >> add_attack_rate[i];
		fatal_hit_prob[i] /= 100.0;
		fatal_hit_damage_ratio[i] /= 100.0;
		add_attack_rate[i] /= 100.0;
	}

	User Cri = User(attack_power[0], strength[0], fatal_hit_prob[0], fatal_hit_damage_ratio[0], add_attack_rate[0]);
	User Faboo = User(attack_power[1], strength[1], fatal_hit_prob[1], fatal_hit_damage_ratio[1], add_attack_rate[1]);

	float pre_CriPower = Cri.getPower();
	float pre_FabooPower = Faboo.getPower();
	Cri.release_equip(attack_power[2], strength[2], fatal_hit_prob[2], fatal_hit_damage_ratio[2], add_attack_rate[2]);
	Cri.equip(attack_power[3], strength[3], fatal_hit_prob[3], fatal_hit_damage_ratio[3], add_attack_rate[3]);

	Faboo.release_equip(attack_power[3], strength[3], fatal_hit_prob[3], fatal_hit_damage_ratio[3], add_attack_rate[3]);
	Faboo.equip(attack_power[2], strength[2], fatal_hit_prob[2], fatal_hit_damage_ratio[2], add_attack_rate[2]);

	float CriPower = Cri.getPower();
	float FabooPower = Faboo.getPower();

	if (pre_CriPower < CriPower)
		cout << "+\n";
	else if (pre_CriPower > CriPower)
		cout << "-\n";
	else
		cout << "0\n";

	if (pre_FabooPower < FabooPower)
		cout << "+\n";
	else if (pre_FabooPower > FabooPower)
		cout << "-\n";
	else
		cout << "0\n";
}

