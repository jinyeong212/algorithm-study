class Solution {
    public long solution(int n, int[] times) {
        long answer = 0L;
        long r = 1_000_000_000L * 1_000_000_000L;
        
        while(answer < r){
            long mid = (answer + r) / 2;
            
            if(getResult(mid, times) >= n)
                r = mid;
            else
                answer = mid + 1;
        }
        return answer;
    }
    
    public long getResult(long time, int[] times){
        long sum = 0;
        
        for(int num : times){
            sum += time/num;
        }
        
        return sum;
    }
}