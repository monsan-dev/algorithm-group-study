/* hanabzu */
/* Programmers 순위 */


#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, vector<vector<int>> results) {
    int answer = 0;
    vector<vector<int>> mat(n,vector<int>(n,0));
    
    for(vector<vector<int>>::iterator it = results.begin(); it != results.end(); it++){
        int winner = (*it)[0]-1;
        int loser = (*it)[1]-1;
        mat[winner][loser] = 1; // win
        mat[loser][winner] = -1; // lose
    }
    
    
    for(int flag = 0; flag < n; flag++){ // update
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(mat[i][flag]==1 && mat[flag][j]==1){ 
                    mat[i][j] = 1;
                }
                else if(mat[i][flag]==-1 && mat[flag][j]==-1){
                    mat[i][j] = -1;
                }
            }
        }
    }
    
    for(vector<vector<int>>::iterator it = mat.begin(); it!= mat.end(); it++){
        if(count((*it).begin(), (*it).end(), 0)==1){
            answer++;
        }
    }
    
    return answer;
}