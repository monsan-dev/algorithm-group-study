import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Comparator;

class Solution {
	
	public class Song implements Comparable<Song>{
		private String genre;
		private int play;
		private int num;

		public Song(String genre, int play, int num) {
			this.genre = genre;
			this.play = play;
			this.num = num;
		}
		public String getGenre() {
			return genre;
		}
		public int getNum() {
			return num;
		}
		
		@Override
		public int compareTo(Song other) {
			if(this.play == other.play)
				return this.num - other.num; // ascending order (by number)
			
			return other.play - this.play; // descending order (by play time)
		}
	}
	
	public int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> genre_map = new HashMap<>();
		List<Song> song_list = new ArrayList<>();
		
		for (int i = 0; i < genres.length; i++) {
			if (!genre_map.containsKey(genres[i])) 
				genre_map.put(genres[i], plays[i]); // make key
			else 
				genre_map.put(genres[i], genre_map.get(genres[i]) + plays[i]); // store value

			song_list.add(new Song(genres[i], plays[i], i));
		}
		
		/* Sort Genres by number of plays */
		List<Entry<String, Integer>> genre_list = new ArrayList<Entry<String, Integer>>(genre_map.entrySet());
		
		Collections.sort(genre_list, new Comparator<Entry<String, Integer>>(){ // sort hash map 
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				return obj2.getValue().compareTo(obj1.getValue()); // descending order
			}
		});
		
		String[] topGenre = new String[genre_list.size()];
		for(int i = 0; i < genre_list.size(); i++) {
			topGenre[i] = genre_list.get(i).getKey(); // Save Top Genres
		}
		
		/* Sort songs by number of plays*/
		Collections.sort(song_list); // sort object
		ArrayList<Integer> topSong_list = new ArrayList<>();
		
		for(String genre: topGenre) {
			int cnt = 0;
			for(Song song: song_list) {
				if(song.getGenre().equals(genre)) {
					topSong_list.add(song.getNum()); // Save Top Songs
					cnt++;
				}
				if(cnt == 2)
					break;
			}
		}
		
		int[] answer = new int[topSong_list.size()];
		for(int i = 0; i < topSong_list.size(); i++) {
			answer[i] = topSong_list.get(i); //convert from ArrayList to Array
		}
		
        return answer;
    }
	
}
/* debugging */
//class Main {
//	public static void main(String[] args){
//		String[] input1 = {"A", "A", "B"};
//		int[] input2 = {600, 500, 300};
//		
//		Solution sol = new Solution();
//		int[] output = sol.solution(input1, input2);
//		for(int i = 0; i < output.length; i++)
//			System.out.println(output[i]);
//	}
//}

