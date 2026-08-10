import java.util.*;


class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for(int i = 0 ; i < clothes.length; i++){
            String str = clothes[i][1];
            
            if(map.get(str) == null)
                map.put(str, 0);
            
            map.put(str, map.get(str) + 1);
        }
        
        for(String key : map.keySet()){
            
            answer *= map.get(key) + 1;
        }
        
        return answer - 1;
    }
}