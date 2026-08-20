import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{0,0});
        
        while(!q.isEmpty()){
            int[] temp = q.poll();
            
            if(temp[1] == numbers.length){
                answer = temp[0] == target ? answer + 1 : answer;
                continue;
            }
            
            q.add(new int[] {temp[0] + numbers[temp[1]], temp[1] + 1});
            q.add(new int[] {temp[0] + (-1 * numbers[temp[1]]), temp[1] + 1});
        }
        
        return answer;
    }
}