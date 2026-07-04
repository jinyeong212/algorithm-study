import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        boolean[] nums = new boolean[200];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0 ; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                int sum = numbers[i] + numbers[j];
                if(!nums[sum]){
                    nums[sum] = true;
                    pq.add(sum);
                }
            }
        }
        
        
        int size = pq.size();
        answer = new int[size];
        
        for(int i = 0 ; i < size; i++){
            int num = pq.poll();
            answer[i] = num;
        }        
        return answer;
    }
}