import java.util.*;

/*
    HashSet에다가 
    각 전화번호의 접두사들을 순차적으로 넣어준다
    
    길이가 긴 순서대로 하면 좋을듯??
*/

class Solution {
    public boolean solution(String[] phone_book) {
        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return -1 * Integer.compare(o1.length(), o2.length());
            }
        });

        for(String str : phone_book){
            pq.add(str);
        }

        HashSet<String> set = new HashSet<>();

        while(!pq.isEmpty()){
            String number = pq.poll();

            if(set.contains(number))
                return false;

            for(int i = 1 ; i <= number.length(); i++){
                set.add(number.substring(0,i));
            }
        }

        return true;
    }
}