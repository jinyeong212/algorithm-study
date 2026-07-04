import java.util.*;

class Solution {
    static boolean isPrime[] = new boolean[50_001];
    static int answer;
    static HashSet<Integer> set;
    
    public int solution(int[] nums) {
        init();
        combination(0,nums,0, 0);            
        return answer;
    }
    
    public void combination(int idx, int[] nums, int sum, int depth){
        if(depth == 3){
            if(!isPrime[sum])
                answer++;
                
          
            return;
        }
            
        for(int i = idx ; i < nums.length; i++){
            combination(i + 1, nums, sum + nums[i], depth + 1);
        }
    }
    
    private void init(){
        for(int i = 2 ; i < 50_001; i++){
            
            if(isPrime[i])
                continue;
            
            for(int j = 2; j * i < 50_000; j++){
                isPrime[j * i] = true;
            }
        }
        
        isPrime[0] = true;
        isPrime[1] = true;
    
        answer = 0;
    }
}