import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int[] memory = new int[32];
	static int pc;
	static int adder;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			pc = 0;
			adder = 0;
			/* input */
			for(int i = 0; i < 32; i++) { // memory cycle
				String line;
				if ((line = br.readLine()) == null){ // if EOF
					return; // terminate
				}
				int commandLine = Integer.parseInt(line, 2); // binary -> decimal
				memory[i] = commandLine;
			}
			while(execution()); // until halted
		}
	}
	
	private static boolean execution() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int type = memory[pc] >> 5;
		int operand = memory[pc] & 31;
		
		pc = (pc + 1) & 31; // pc++

		switch (type) {
		case 0: // STA
			memory[operand] = adder;
			break;
		case 1: // LDA
			adder = memory[operand];
			break;
		case 2: // BEQ
			if(adder == 0)
				pc = operand;
		case 3: // NOP
			break;
		case 4: // DEC
			adder--;
			if (adder < 0)
				adder = 255;
			break;
		case 5: // INC
			adder = (adder + 1) & 255;
			break;
		case 6: // JMP
			pc = operand;
			break;
		case 7: // HLT
			/* output */
			String binary = Integer.toString(adder,2);
			adder = Integer.parseInt(binary);
			String result = String.format("%08d", adder);
		    result += "\n"; // decimal -> binary
			bw.write(result);
			bw.flush();
			return false;
		}
		return true;
	}
	
}
