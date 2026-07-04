class Solution {
    final int NUM = 1_000_000_007;
    
    public int solution(int n) {
        int answer = 0;
        
        if(n % 2 == 1)
            return 0;
        
        long dp[] = new long [n + 1];
        
        dp[2] = 3;
        
        for(int i = 4; i <= n; i += 2){
            long sum = (dp[i - 2] * 3) % NUM;
            
            for(int j = 4; i - j >= 2; j += 2){
                sum += (dp[i - j] * 2) % NUM;
            }
            
            dp[i] = ((sum + 2) % NUM);
        }
        
        return (int)dp[n];
    }
}