import java.util.*;

/*
    순서대로 넣어주기 자신의 우선순위가 아니라면 뒤로 빼주기
    현재 실행해야하는 우선순위 가지는 PriorityQueue 가 필요
    
    현재 순서를 나타내는 Queue 필요
*/
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<int []> q = new ArrayDeque<>();
        int[] cnt = new int[10];
        int targetPriority = 0;
        
        for(int i = 0; i < priorities.length; i++){
            q.add(new int[]{i, priorities[i]});
            cnt[priorities[i]]++;
            targetPriority = Math.max(targetPriority, priorities[i]);
        }
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            
            
            if(now[1] == targetPriority){
                cnt[targetPriority]--;
                answer++;
                if(now[0] == location){
                    
                    break;
                }
                    
                
                while(cnt[targetPriority] == 0 && targetPriority != 0)
                    targetPriority--;
                
                continue;
            }
            
            q.add(now);
        }
        
        return answer;
    }
}