class Solution {
    int[][][] dp;
    
    public int solution(int[] money) {
        //첫집을 훔치냐
        int case1 = rob(money, 0, money.length - 2);
        //안훔치냐
        int case2 = rob(money, 1, money.length - 1);
        
        return Math.max(case1, case2);
    }
    
    private int rob(int[] money, int start, int end) {
        int[] dp = new int[money.length];

        dp[start] = money[start];

        if (start + 1 <= end) {
            dp[start + 1] = Math.max(money[start], money[start + 1]);
        }

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(
                dp[i - 1],
                dp[i - 2] + money[i]
            );
        }

        return dp[end];
    }
}