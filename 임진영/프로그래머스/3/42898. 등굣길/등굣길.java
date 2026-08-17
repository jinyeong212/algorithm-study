class Solution {
    int DIVIDE_NUM = 1_000_000_007;
    int[][] map;
    
    int[] dr = {0 , 1};
    int[] dc = {1 , 0};
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        map = new int[m + 1][n + 1];
        
        map[1][1] = 1;
        
        for(int[] puddle : puddles){
            map[puddle[0]][puddle[1]] = -1;
        }
        
        for(int i = 1 ; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(map[i][j] < 0)
                    continue;
                
                for(int k = 0; k < 2; k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    
                    if(!isValid(nr, nc, m, n))
                        continue;
                    
                    map[nr][nc] = num(map[nr][nc], map[i][j]);
                }
            }
        }
        
        return map[m][n];
    }
    
    public boolean isValid(int nr, int nc, int m, int n){
        return nr <= m && nc <= n && map[nr][nc] >= 0;
    }
    
    public int num(int n1, int n2){
        return (n1 % DIVIDE_NUM + n2 % DIVIDE_NUM) % DIVIDE_NUM;
    }
}