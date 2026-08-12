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
                remove(asc);
            } else{
                remove(desc);
            }
        }
        
        return new int[] {peek(desc), peek(asc)};
    }
    
    public void insert(int num){
        asc.add(num);
        desc.add(num);
        
        if(map.get(num) == null) map.put(num, 0);
        
        map.put(num, map.get(num) + 1);
    }
    
    public void remove(PriorityQueue<Integer> pq){
        while(!pq.isEmpty()){
            int num = pq.poll();
            
            if(map.get(num) == 0) continue;
            
            map.put(num, map.get(num) - 1);
            break;
        }
    }
    
    public int peek(PriorityQueue<Integer> pq){ 
        
        while(!pq.isEmpty()){
            int temp = pq.poll();
            
            if(map.get(temp) == 0) continue;
            
            return temp;
        }
        
        return 0;
    }
    
    public void init(){
        map = new HashMap<Integer, Integer>();
        asc = new PriorityQueue<Integer>();
        desc = new PriorityQueue<Integer>((n1, n2) -> Integer.compare(n2, n1));
    }
}