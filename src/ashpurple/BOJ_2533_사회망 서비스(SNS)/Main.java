import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class Main {
	
	static int N;
	static LinkedList<Integer>[] edge;
	static boolean[] visit;
	// EA = early adaptor
	static int[] normal; // when normal node, cumulative EA count value from child to current
	static int[] early; // when EA node, cumulative EA count value from child to current
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* input */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		
		edge = new LinkedList[N + 1];
		visit = new boolean[N + 1];
		normal = new int[N + 1];
		early = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			edge[i] = new LinkedList<Integer>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			edge[u].add(v);
			edge[v].add(u);
		}
		
		dfs(1); // starts with first node
		
		/* output */
		String result;
		if(normal[1] < early[1])
			result = itos(normal[1]);
		else
			result = itos(early[1]);

		bw.write(result);
		bw.flush();
	}
	
	public static void dfs(int cur) {
		visit[cur] = true;
		early[cur] = 1; // if current is EA
		normal[cur] = 0; // if current is normal
		
		for(int child: edge[cur]) {
			if(!visit[child]) { // if hadn't visited
				dfs(child);
				
				// if current is normal, child must be EA
				normal[cur] += early[child];
				// if current is EA, choose the minimum of child's status
				early[cur] += min(normal[child], early[child]); 
				
			}
		}
	}
	
	private static int min(int a, int b) {
		if(a < b)
			return a;
		else
			return b;
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static String itos(int num) {
		return String.valueOf(num);
	}
}

