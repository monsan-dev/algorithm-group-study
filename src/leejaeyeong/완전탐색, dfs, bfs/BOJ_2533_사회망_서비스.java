import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2533_사회망_서비스 {
	static int count;
	static List<Integer>[] tree;
	static boolean[] earlyAdopter, visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = stoi(br.readLine());
		tree = new ArrayList[n + 1];
		earlyAdopter = new boolean[n + 1]; // 얼리어답터 목록
		visited = new boolean[n + 1];
		
		// 트리 구성
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			tree[u].add(v);
			tree[v].add(u);
		}
		
		treeSearch(1); // 1번 노드부터 탐색
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			if (earlyAdopter[i]) { // 얼리어답터 수 카운트
				++answer;
			}
		}
		System.out.println(answer);
	}
	
	
	private static boolean treeSearch(int root) {
		visited[root] = true;
		
		if (tree[root].isEmpty()) { // 단말노드인 경우 
			return true; 
		}
		
		for (int node : tree[root]) {
			if (!visited[node]) { // 아직 처리하지 않은 경우 
				if (treeSearch(node) || !earlyAdopter[node]) { // node가 단말 노드이거나, 얼리어답터가 아닌 경우
					earlyAdopter[root] = true; // 부모 노드를 얼리어답터로 처리
				} 
			}
		}
		return false;
	}


	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}	
