import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int time = 0;
        int nowWeight = truck_weights[0];
        int index = 1;
        
        Queue<int []> q = new ArrayDeque<>();
        q.add(new int[]{time, truck_weights[0]});
        
        
        while(index < truck_weights.length || !q.isEmpty()){
            if(!q.isEmpty()){
                if(time - q.peek()[0] >= bridge_length){
                    nowWeight -= q.poll()[1];
                }
            }
            
            if(index < truck_weights.length){
                if(nowWeight + truck_weights[index] <= weight && bridge_length >= q.size() + 1){
                    q.add(new int[]{time, truck_weights[index]});
                    nowWeight += truck_weights[index++];
                }
            }
            
            time++;
        }
        
        return time;
    }
}