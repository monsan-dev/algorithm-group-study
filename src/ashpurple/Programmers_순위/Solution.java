class Solution {
	public int solution(int n, int[][] results) {
        boolean[][] matrix = new boolean[n + 1][n + 1];
        
        for(int i = 0; i < results.length; i++){
            int v = results[i][0];
            int u = results[i][1];
            matrix[v][u] = true;
        }
        
        Floyd(n, matrix);
        
        int answer = 0;
        for(int i = 1; i <= n; i++) {
        	int connect = 1;
        	for(int j = 1; j <= n; j++) {
        		if(matrix[i][j] | matrix[j][i])
        			connect++;
        	}
        	if(connect == n) // fully connected
        		answer++;
        }
        return answer;
    }
    
    public void Floyd(int N, boolean[][] matrix) {
    	for (int k = 1; k <= N; k++) {
    		for (int i = 1; i <= N; i++){
    			for (int j = 1; j <= N; j++){
    				if (matrix[i][k] & matrix[k][j] ) // A -> B, B -> C
    					matrix[i][j] = true; // A -> C
    			}
    		}
    	}
    }
}
