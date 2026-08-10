import java.util.*;

/*
    정렬 대상 : 노래, 장르
    
    노래는 스코어 사용
    장르는 count를 세면 됨
*/

class Solution {
    class Score{
        int plays, num;
        
        Score(int plays, int num){
            this.plays  = plays;
            this.num    = num;
        }
    }
    
    class GenrePlay{
        String name;
        int play;
        
        GenrePlay(String name, int play){
            this.name = name;
            this.play = play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, PriorityQueue<Score>> songs = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            String key = genres[i];
            if(songs.get(key) == null){
                songs.put(key, new PriorityQueue<Score>(new Comparator<Score>(){
                    @Override
                    public int compare(Score s1, Score s2){
                            if(s1.plays == s2.plays)
                                return Integer.compare(s1.num, s2.num);
                        
                        return -1 * Integer.compare(s1.plays, s2.plays);
                    }
                }));
            }
            
            songs.get(key).add(new Score(plays[i], i));
            
            if(count.get(key) == null)
                count.put(key, 0);
            
            count.put(key, count.get(key) + plays[i]);
        }
        
        PriorityQueue<GenrePlay> pq = new PriorityQueue<>(new Comparator<GenrePlay>(){
            @Override
            public int compare(GenrePlay g1, GenrePlay g2){
                return -1 * Integer.compare(g1.play, g2.play);
            }
        });
    
        ArrayList<Integer> result = new ArrayList<>();
        
        for(String key : count.keySet()){
            pq.add(new GenrePlay(key, count.get(key)));
        }
        

        while(!pq.isEmpty()){
            String key = pq.poll().name;
            
            PriorityQueue<Score> scores = songs.get(key);

            for(int j = 0; j < 2 && !scores.isEmpty(); j++){
                result.add(scores.poll().num);
            }
        }
        
        int[] answer = new int [result.size()];
        
        for(int i = 0 ; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}