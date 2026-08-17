/*
    0
    0 1
    0 1 2 
    0 1 2 3
    
    자기 자신과 +1 에 더해주며 max값 가지고 내려가기
*/


class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int size = triangle.length;
        
        int[][] dp = new int[size][size];
        dp[0][0] = triangle[0][0];
        
        for(int i = 0; i < triangle.length - 1; i++){
            for(int j = 0; j < triangle[i].length; j++){
                dp[i + 1][j] = Math.max(triangle[i + 1][j] + dp[i][j], dp[i + 1][j]);
                dp[i + 1][j + 1] = Math.max(triangle[i + 1][j + 1] + dp[i][j], dp[i + 1][j + 1]);
            }
        }
        
        for(int i = 0; i < size; i++){
            answer = Math.max(answer, dp[size - 1][i]);
        }
        
        return answer;
    }
}