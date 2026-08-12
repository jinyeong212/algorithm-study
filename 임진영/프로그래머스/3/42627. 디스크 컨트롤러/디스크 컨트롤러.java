import java.util.*;

class Solution {
    //int[] {작업 시간, index, 요청 시각}
    PriorityQueue<int []> jobOrders;
    PriorityQueue<int []> waitQueue;
    int time, sum;
    
    public int solution(int[][] jobs) {
        init();
        
        for(int i = 0; i < jobs.length; i++){
            jobOrders.add(new int[] {jobs[i][1], i, jobs[i][0]});
        }
        
        //case1 : 대기큐에 아무것도 없을 경우 
        //        작업 순서에서 빼서 넣어줘야함 - time 조건 걸어주기
        //case2 : 대기큐에 작업이 있을 경우 우선순위가 가장 높은 것 하나 빼서 작업하기
        //        작업 순서 time에 안되게 넣어주기
        while(!jobOrders.isEmpty()){
            if(waitQueue.isEmpty()){
                int[] job = jobOrders.peek();
                
                time = Math.max(time, job[2]); // 시간 보정하기
            }else{
                int[] job = waitQueue.poll();
                time += job[0];
                
                sum += time - job[2];
            }
        
            while(!jobOrders.isEmpty() && jobOrders.peek()[2] <= time){
                waitQueue.add(jobOrders.poll());
            }    
            
        }
        
        while(!waitQueue.isEmpty()){
            int[] job = waitQueue.poll();
            time += job[0];
            
            sum += time - job[2];;
        }
        
        return sum / jobs.length;
    }
    
    public void init(){
        jobOrders = new PriorityQueue<int []>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        
        waitQueue = new PriorityQueue<int []>((o1, o2) -> {                               
            if(o1[0] == o2[0]){
                if(o1[2] == o2[2])
                    return Integer.compare(o1[1], o2[1]);
                
                return Integer.compare(o1[2], o2[2]);
            }
            return Integer.compare(o1[0], o2[0]);
        });
        
        time = sum = 0;
    }
}