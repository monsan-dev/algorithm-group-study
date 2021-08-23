import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public class Point {
    	public int x;
    	public int y;
    	public Point(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    public class Block {
    	public int[][] blockMatrix;
    	public int row;
    	public int col;
    	public int num;
    	
    	public Block(int[][] blockMatrix, int row, int col, int num) {
    		this.blockMatrix = blockMatrix;
    		this.row = row;
    		this.col = col;
    		this.num = num;
    	}
    }
	
    public int solution(int[][] game_board, int[][] table) {
        int size = game_board.length;
        ArrayList<Block> boardBlockList = new ArrayList<Block>();
        ArrayList<Block> tableBlockList = new ArrayList<Block>();;
        
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                game_board[i][j] ^= 1; // XOR
        
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (game_board[i][j] == 1) // extract board block
                	boardBlockList.add(bfs(game_board, i, j));
        
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (table[i][j] == 1) // extract table block
                	tableBlockList.add(bfs(table, i, j));
        
        int answer = 0;
        for(Block boardBlock : boardBlockList) {
        	Loop:
        	for(Iterator<Block> iter = tableBlockList.iterator(); iter.hasNext();) {
        		Block tableBlock = iter.next();
        		for(int i = 0; i < 4; i++) {
        			if(isMatch(boardBlock, tableBlock)) { // compare
        				answer += boardBlock.num;
        				iter.remove(); // remove completed table block
        				break Loop; // go to next board block
        			}
        			rotateBlock(tableBlock); 
        		}
        	}
        }
        return answer;
    }
    
    boolean isMatch(Block a, Block b) { // is same block
    	if(a.num == b.num) {
    		if(a.row == b.row && a.col == b.col) {
    			for(int i = 0; i < a.row; i++) {
    				for(int j = 0; j < b.col; j++) 
    					if(a.blockMatrix[i][j] != b.blockMatrix[i][j])
    						return false;
    			}
    			return true;
    		}
    	}
    	return false;
    }

    void rotateBlock(Block block) { // rotate 90 degree
    	int row = block.col;
    	int col = block.row;
    	int[][] origin = block.blockMatrix;
        int[][] rotate = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                rotate[i][j] = origin[col - 1 - j][i];
        
        block.row = row;
        block.col = col;
        block.blockMatrix = rotate;
    }

    Block bfs(int[][] board, int x, int y) { // find block
        int dx[] = new int[]{1, -1, 0, 0};
        int dy[] = new int[]{0, 0, 1, -1};
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        
        ArrayList<Point> pointList = new ArrayList<>();;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        
        board[x][y] = 0; // initial visit

        while (!q.isEmpty()) {
            Point p = q.poll();
            minX = Math.min(p.x, minX);
            minY = Math.min(p.y, minY);
            maxX = Math.max(p.x, maxX);
            maxY = Math.max(p.y, maxY);
            pointList.add(new Point(p.x, p.y));
            
            for (int i = 0; i < 4; i++) {
                int nextX = p.x + dx[i];
                int nextY = p.y + dy[i];
                if (0 <= nextX && 0 <= nextY && nextX < board.length && nextY < board.length) { // isValid
                	if (board[nextX][nextY] == 1) { // isBlock
                		board[nextX][nextY] = 0; // visit
                        q.add(new Point(nextX, nextY)); // add new point
                	}
                }
            }
        }
        return makeBlock(minX, minY, maxX, maxY, pointList);
    }

    Block makeBlock(int minX, int minY, int maxX, int maxY, ArrayList<Point> pointList) { // make block
    	int row = maxX - minX + 1;
    	int col = maxY - minY + 1;
        int[][] blockArr = new int[row][col];
        for (Point p : pointList)
            blockArr[p.x - minX][p.y - minY] = 1;
        Block block = new Block(blockArr, row, col, pointList.size());
        
        return block;
    }
	
}