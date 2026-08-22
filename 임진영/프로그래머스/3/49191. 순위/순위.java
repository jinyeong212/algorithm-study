class Solution {
    boolean[][] result;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        result = new boolean[n][n];
        
        for(int[] arr : results){
            result[arr[0] - 1][arr[1] - 1] = true;
        }
        
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    result[i][j] = result[i][j] || (result[i][k] && result[k][j]);
                }
            }    
        }
        
        for(int i = 0 ; i < n ; i++){
            int winCnt = 0;
            int lossCnt = 0;
            
            for(int j = 0 ;  j < n; j++){
                if(result[i][j]) winCnt++;
                if(result[j][i]) lossCnt++;
            }
            
            if(winCnt + lossCnt == n - 1)
                answer++;
        }
        
        return answer;
    }
}