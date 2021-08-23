/* hanabzu */
/* programmers 베스트 앨범 */

#include <string>
#include <vector>
#include <utility>
#include <map>
#include <algorithm>
#include <set>
using namespace std;


bool vec_cmp(const pair<string, int>& a, const pair<string, int>& b) { // map->vec sort
    return a.second > b.second;
}

struct set_cmp {
    bool operator() (const pair<int, int>& a, const pair<int, int>& b) const {
        if (a.first == b.first) {
            return a.second < b.second; // song index
        }
        else {
            return a.first > b.first; // play count 
        }
    }
};

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    map<string, int> played;
    map<string, set<pair<int, int>, set_cmp>> songs;
    for (int i = 0; i < genres.size(); i++) {
        if (songs.count(genres[i])) { // already mapped
            songs[genres[i]].insert(make_pair(plays[i], i)); // sort playing count of songs group by genres
            played[genres[i]] += plays[i];
        }
        else {
            songs[genres[i]] = set<pair<int, int>, set_cmp>();
            songs[genres[i]].insert(make_pair(plays[i], i));
            played[genres[i]] = plays[i];
        }
    }

    vector<pair<string, int>> v_played(played.begin(), played.end());
    sort(v_played.begin(), v_played.end(), vec_cmp);
    for (auto p : v_played) {
        answer.push_back((*songs[p.first].begin()).second);
        if (songs[p.first].size() > 1) {
            answer.push_back((*(++songs[p.first].begin())).second);
        }

    }
    return answer;
}