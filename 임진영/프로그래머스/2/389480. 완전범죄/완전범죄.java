class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;
        
        for(int [] item : info){
            boolean[][] nextState = new boolean[n][m];
            for(int a = 0; a < n; a++){
                for(int b = 0; b < m; b++){
                    
                    //방문한 적이 없는 상태값 넘어가기
                    if(!dp[a][b])
                        continue;
                    
                    //현재 상태값에서 훔치기
                    int nextA = a + item[0];
                    int nextB = b + item[1];
                    
                    //A를 훔친다면?
                    if(nextA < n && dp[a][b])
                        nextState[nextA][b] = true;
                        
                    //B를 훔친다면?
                    if(nextB < m && dp[a][b])
                        nextState[a][nextB] = true;
                }
            }
            dp = nextState;
        }
        
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){
                if(dp[i][j])
                    return i;
            }
        }
        
        return -1;
    }
}