class Solution {
    static int[] diffs, times;
    
    public int solution(int[] diffs1, int[] times1, long limit) {
        int answer = 0;
        
        diffs = diffs1;
        times = times1;
        
        int l = 1;
        int r = 100_001;
        
        while(l <= r){
            int mid = (l + r) / 2;
            
            long result = getTime(mid);
            
            if(result <= limit)
                r = mid - 1;
            else
                l = mid + 1;
            
            //System.out.println(l + " " +  r);
        }
        
        return l;
    }

    public long getTime(int level){
        long time = diffs[0] > level 
            ? times[0] + times[0] * (diffs[0] - level)
            : times[0];
        
        for(int i = 1; i < diffs.length; i++){
            int diff = diffs[i];
            
            time += diffs[i] > level 
            ? times[i] + (times[i] + times[i - 1]) * (diffs[i] - level)
            : times[i];
        }
        
        return time;
    }
}