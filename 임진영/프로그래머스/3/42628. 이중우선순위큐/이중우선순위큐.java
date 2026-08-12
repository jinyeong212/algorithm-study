import java.util.*;
import java.io.*;

class Solution {
    HashMap<Integer, Integer> map;
    PriorityQueue<Integer> asc;
    PriorityQueue<Integer> desc;
    
    public int[] solution(String[] operations) throws Exception {
        init();
        
        for (String operation : operations) {
            if (operation.startsWith("I ")) {
                int n = Integer.parseInt(operation.substring(2));
                insert(n);
            } else if (operation.equals("D -1")) {
                removeMin();
            } else{
                removeMax();
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
        int min = 0;
        
        while(!desc.isEmpty()){
            int temp = desc.poll();
            
            if(map.get(temp) == 0)
                continue;
            
            max = temp;
            
            break;
        }
        
        while(!asc.isEmpty()){
            int temp = asc.poll();
            
            if(map.get(temp) == 0)
                continue;
            
            min = temp;
            
            break;
        }
        
        
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