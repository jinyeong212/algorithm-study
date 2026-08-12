import java.util.*;
import java.io.*;

class Solution {
    HashMap<Integer, Integer> map;
    PriorityQueue<Integer> asc;
    PriorityQueue<Integer> desc;
    
    public int[] solution(String[] operations) throws Exception {
        init();
        
        for(String str : operations){
            StringTokenizer st = new StringTokenizer(str);
            
            String command = st.nextToken();
            
            if(command.equals("I"))
                insert(Integer.parseInt(st.nextToken()));
            else{
                if(st.nextToken().equals("1"))
                    removeMax();
                else
                    removeMin();
            }
                
        }
        
        return getAnswer();
    }
    
    public void insert(int num){
        asc.add(num);
        desc.add(num);
        
        if(map.get(num) == null)
            map.put(num, 0);
        
        map.put(num, map.get(num) + 1);
    }
    
    public int[] getAnswer(){
        int max = 0;
        int min = Integer.MAX_VALUE;
        
        for(int num : map.keySet()){
            if(map.get(num) == 0) continue;
            
            max = Math.max(num, max);
            min = Math.min(num, min); 
        }
        
        if(max < min)
            return new int[] {0, 0};
        
        return new int[] {max, min};
    }
    
    public void removeMax(){
        while(!desc.isEmpty()){
            int max = desc.poll();
            
            if(map.get(max) == 0)
                continue;
            
            map.put(max, map.get(max) - 1);
            
            break;
        }
    }
    
    public void removeMin(){
        while(!asc.isEmpty()){
            int min = asc.poll();
            
            if(map.get(min) == 0)
                continue;
            
            map.put(min, map.get(min) - 1);
            
            break;
        }
    }
    
    public void init(){
        map = new HashMap<Integer, Integer>();
        asc = new PriorityQueue<Integer>();
        desc = new PriorityQueue<Integer>((n1, n2) -> Integer.compare(n2, n1));
    }
}