/*
백 트래킹 dfs로 들어가서 도달 했을때 검사하기
*/

class Solution {
    int R, C, answer;
    int checked[][];
    
    //번호 별 방향 진입방향[0] 상 하 좌 우 순서
    //[번호][이동 가능 방향 개수][0 = 들어온 방향 1 = r 이동 2 = c 이동 3 = 다음 입장에서 들어온 방향]
    int[][][] direction = {
        {{0}},
        {{2, 0, 1, 2}, {3, 0, -1, 3}},
        {{0, 1, 0, 0}, {1, -1, 0, 1}},
        {{2, 0, 1, 2}, {3, 0, -1, 3}, {0, 1, 0, 0}, {1, -1, 0, 1}},
        {{2, -1, 0, 1}, {0, 0, -1, 3}},
        {{0, 0, 1, 2}, {3, -1, 0, 1}},
        {{1, 0, 1, 2}, {3, 1, 0, 0}},
        {{2, 1, 0, 0}, {1, 0, -1, 3}}
    };
    
    int[][] canDirection = {
        {2, 3, 4, 5},
        {2, 3, 6, 7},
        {1, 3, 4, 7},
        {1, 3, 5, 6}
    };
    
    static int[][] grid;
    
    public int solution(int[][] grid1) {
        answer = 0;
        
        int cnt = init(grid1);
        
        //시작은 항상 좌
        dfs(0, 0, 2, cnt, 0);
        
        return answer;
    }
    
    private void dfs(int r, int c, int pre, int cnt, int used) {
        if(!isValid(r, c) || grid[r][c] == -1)
            return;
        
        int axis = getAxis(pre);
        
        if(checked[r][c] == 3 || checked[r][c] == axis)
            return;
        
        //현재 레일 번호
        int cur = grid[r][c];
        
        if(r + 1 == R && c + 1 == C) {
            for(int i = 0; i < direction[cur].length; i++) {
                if(pre != direction[cur][i][0])
                    continue;
                
                if(cnt == used + 1)
                    answer++;
                
                break;
            }
            
            return;
        }
        
        //선로가 없는 경우
        if(cur == 0) {
            for(int temp : canDirection[pre]) {
                for(int i = 0; i < direction[temp].length; i++) {
                    if(pre != direction[temp][i][0])
                        continue;
                    
                    int nr = r + direction[temp][i][1];
                    int nc = c + direction[temp][i][2];
                    int next = direction[temp][i][3];
                    
                    if(!isValid(nr, nc) || grid[nr][nc] == -1)
                        continue;
                    
                    grid[r][c] = temp;
                    
                    if(temp == 3) {
                        checked[r][c] += axis;
                        dfs(nr, nc, next, cnt + 1, used);
                        checked[r][c] -= axis;
                    } else {
                        checked[r][c] = 3;
                        dfs(nr, nc, next, cnt, used);
                        checked[r][c] = 0;
                    }
                    
                    grid[r][c] = 0;
                }
            }
        }
        //정해진 선로가 있는 경우
        else {
            for(int i = 0; i < direction[cur].length; i++) {
                if(pre != direction[cur][i][0])
                    continue;
                
                int nr = r + direction[cur][i][1];
                int nc = c + direction[cur][i][2];
                int next = direction[cur][i][3];
                
                if(!isValid(nr, nc) || grid[nr][nc] == -1)
                    continue;
                
                if(cur == 3) {
                    checked[r][c] += axis;
                    dfs(nr, nc, next, cnt, used + 1);
                    checked[r][c] -= axis;
                } else {
                    checked[r][c] = 3;
                    dfs(nr, nc, next, cnt, used + 1);
                    checked[r][c] = 0;
                }
            }
        }
    }
    
    private int init(int[][] grid1) {
        grid = grid1;
        
        R = grid.length;
        C = grid[0].length;
        
        checked = new int[R][C];
        
        int cnt = 0;
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(grid[i][j] != 0 && grid[i][j] != -1) {
                    cnt++;
                    
                    if(grid[i][j] == 3)
                        cnt++;
                }
            }
        }
        
        return cnt;
    }
    
    private int getAxis(int pre) {
        if(pre == 0 || pre == 1) //상 하 1
            return 1;
        
        return 2; //좌 우 2
    }
    
    private boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }
}