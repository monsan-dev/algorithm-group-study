import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11387_님_무기가_좀_나쁘시네여 {
	static class Status {
		int physicalDamage; // 공격력
		int power; // 힘
		float criticalProbability; // 치명타 확률
		float criticalRate; // 치명타 비율
		float speedOfAttack; // 공격 속도
		float originPowerLevel; // 기존 장비를 착용 시 전투력
		float powerLevel; // 비교되는 장비 착용 시 전투력 
		
		Status(int physicalDamage, int power, int criticalProbability, int criticalRate, int speedOfAttack) {
			this.physicalDamage = physicalDamage;
			this.power = power;
			this.criticalProbability = criticalProbability;
			this.criticalRate = criticalRate;
			this.speedOfAttack = speedOfAttack;
		}
		
		private float computePowerLevel() {
			float tempCriticalProbability = (float)criticalProbability / 100;
			float tempCriticalRate = (float)criticalRate / 100;
			float tempSpeedOfAttack = (float)speedOfAttack / 100;
			// 전투력 계산 후 반환
			return physicalDamage * (1 + (float)power / 100) * ((1 - Math.min(tempCriticalProbability, 1)) + Math.min(tempCriticalProbability, 1) * tempCriticalRate) * (1 + tempSpeedOfAttack);
		}
		
		public void comparePowerLevel(Status wearingEquipmentStatus, Status otherEquipmentStatus) {
			originPowerLevel = computePowerLevel(); // 기존 장비의 전투력 계산
			
			// 착용 중인 장비와 상대방의 장비의 능력치의 차이를 반영
			physicalDamage += otherEquipmentStatus.physicalDamage - wearingEquipmentStatus.physicalDamage;
			power += otherEquipmentStatus.power - wearingEquipmentStatus.power;
			criticalProbability += otherEquipmentStatus.criticalProbability - wearingEquipmentStatus.criticalProbability;
			criticalRate += otherEquipmentStatus.criticalRate - wearingEquipmentStatus.criticalRate;
			speedOfAttack += otherEquipmentStatus.speedOfAttack - wearingEquipmentStatus.speedOfAttack;
			
			powerLevel = computePowerLevel(); // 다른 장비 착용 시 전투력 계산
			
			// 결과 출력
			String ret = null;
			if (originPowerLevel > powerLevel) ret = "-";
			else if (originPowerLevel < powerLevel) ret = "+";
			else ret = "0";
			System.out.println(ret);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 크리의 정보 input
		StringTokenizer st = new StringTokenizer(br.readLine());
		Status cri = new Status(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		
		// 파부의 정보 input
		st = new StringTokenizer(br.readLine());
		Status pabu = new Status(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		
		// 크리의 장비 정보 input
		st = new StringTokenizer(br.readLine());
		Status criEquipment = new Status(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		
		// 파부의 장비 정보 input
		st = new StringTokenizer(br.readLine());
		Status pabuEquipment = new Status(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		
		cri.comparePowerLevel(criEquipment, pabuEquipment);
		pabu.comparePowerLevel(pabuEquipment, criEquipment);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
