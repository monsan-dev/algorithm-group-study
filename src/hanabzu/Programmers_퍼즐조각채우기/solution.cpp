/* hanabzu */
/* programmers 퍼즐 조각 채우기 */

#include <string>
#include <vector>
#include <utility>
#include <queue>
#include <set>

using namespace std;

typedef pair<int, int> point;
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };

class Block {
public:
    bool used = 0;
    int size = 0;
    set<point> points;

    void add_point(point p) {
        points.insert(p);
        size++;
    }

    void rotate() { // rotate 90 degrees for all points
        set<point> new_points;
        for (set<point>::iterator it = points.begin(); it != points.end(); it++) {
            new_points.insert(make_pair(-(*it).second, (*it).first));
        }
        points.clear();
        
        point bias = *(new_points.begin()); // the first point of the set must be (0,0)
        for (set<point>::iterator it = new_points.begin(); it != new_points.end(); it++) {
            points.insert(make_pair((*it).first - bias.first, (*it).second - bias.second));
        }
    }

    bool operator==(Block& another) {
        if (this->size != another.size) {
            return false;
        }
        for (set<point>::iterator it1 = this->points.begin(), it2 = another.points.begin();
            it1 != this->points.end(); it1++, it2++) { // compare all points
            if (*it1 != *it2) { 
                return false;
            }
        }
        return true;
    }
};

vector<Block> blocks;
void find_blocks(vector<vector<int>>& table);
Block bfs(int row, int col, vector<vector<int>>& table, int flag);
int match_blocks(vector<vector<int>>& board);

void find_blocks(vector<vector<int>>& table) {
    for (int row = 0; row < table.size(); row++) {
        for (int col = 0; col < table.size(); col++) {
            if (table[row][col] == 1) {
                blocks.push_back(bfs(row, col, table, 1));
            }
        }
    }
}

Block bfs(int row, int col, vector<vector<int>>& table, int flag) { // table flag = 1, board flag = 0
    Block b;
    queue<point> q;
    q.push(make_pair(row, col));
    table[row][col] = (flag + 1) % 2;

    while (!q.empty()) {
        point u = q.front();
        
        for (int i = 0; i < 4; i++) {
            int new_x = u.first + dx[i];
            int new_y = u.second + dy[i];
            if (new_x >= 0 && new_x < table.size() && new_y >= 0 && new_y < table.size()) { // boundary check
                if (table[new_x][new_y] == flag) {
                    table[new_x][new_y] = (flag + 1) % 2;
                    q.push(make_pair(new_x, new_y));
                }
            }
        }
        b.add_point(make_pair(u.first - row, u.second - col)); // add point to block
        q.pop();
    }

    return b;
}

int match_blocks(vector<vector<int>>& board) {
    int answer = 0;
    for (int row = 0; row < board.size(); row++) {
        for (int col = 0; col < board.size(); col++) {
            if (board[row][col] == 0) {
                Block space = bfs(row, col, board, 0); // new empty space
                for (int i = 0; i < blocks.size(); i++) { // match
                    if (space.used == false && blocks[i].used == false) {
                        for (int j = 0; j < 4; j++) {
                            if (space == blocks[i]) {
                                blocks[i].used = true;
                                space.used = true;
                                answer += blocks[i].size; // score update
                                break;
                            }
                            blocks[i].rotate();
                        }
                    }
                }
            }
        }
    }
    return answer;
}

int solution(vector<vector<int>> game_board, vector<vector<int>> table) {
    find_blocks(table);
    return match_blocks(game_board);
}




