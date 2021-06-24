import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6568_귀도_반_로썸은_크리스마스날_심심하다고_파이썬을_만들었다 {
	static final int SIZE = 32;
	static int buffer, pc;
	static int[] memory = new int[SIZE];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			int lineNum = 0;
			while ((input = br.readLine()) != null) {
				memory[lineNum++] = Integer.parseInt(input, 2); // ex) 00000011 --> 3
				if (lineNum == SIZE) { // 32byte 단위로 처리 
					pc = buffer = lineNum = 0;
					while(process(memory[pc] / SIZE, memory[pc] % SIZE)); // 명령어 부분과 메모리 주소 처리
				}
			}
			System.out.println(sb.toString());
			return;
		}
	}
	private static boolean process(int cmd, int val) {
		pc = ++pc % SIZE;
		switch(cmd) {
		case 0: memory[val] = buffer; break;
		case 1: buffer = memory[val]; break;
		case 2: if (buffer == 0) pc = val; break;
		case 4: buffer = (buffer + 255) % 256; break;
		case 5: buffer = (buffer + 1) % 256; break;
		case 6: pc = val; break;
		case 7: 
			for (int i = 7; i >= 0; i--) {
				sb.append((buffer >> i) & 1);
			}
			sb.append("\n");
			return false;
		}
		return true;
	}
}
