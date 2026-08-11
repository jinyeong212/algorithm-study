import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        for(int i = 0; i < prices.length; i++){
            while(!q.isEmpty() && prices[q.peekLast()] > prices[i]){
                int index = q.pollLast();
                answer[index] = i - index;
            }
            q.add(i);
        }
        
        while(!q.isEmpty()){
            int index = q.poll();
            answer[index] = prices.length - 1 - index;
        }
        
        return answer;
    }
}