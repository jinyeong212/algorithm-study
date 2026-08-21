import java.util.*;

class Solution {
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int C, R;
    boolean[][] visited;
    
    public int solution(int[][] maps) {       
        init(maps);
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{0,0,1});
        
        while(!q.isEmpty()){
            int[] temp = q.poll();
            
            if(temp[0] == R - 1 && temp[1] == C -1)
                return temp[2];
        
            for(int i = 0; i < 4; i++){
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                
                if(isValid(nr,nc) && !visited[nr][nc] && maps[nr][nc] == 1){
                    q.add(new int[]{nr, nc, temp[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
            
        return -1;
    }
    
    public boolean isValid(int nr, int nc){
        return nr >= 0 && nc >= 0 && nr < R && nc < C;
    }
    
    public void init(int[][] maps){
        R = maps.length;
        C = maps[0].length;
    
        visited = new boolean[R][C];
        visited[0][0] = true;
    }
}