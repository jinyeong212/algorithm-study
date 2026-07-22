import java.util.*;

class Solution {
    int[][] map;
    int[][] compressedRowMap;
    int[][] compressedMap;
    
    public int[] solution(int n, int m, int h, int w, int[][] drops) {
        int[] answer = {};
        
        init(n,m,drops,h,w);
        
        for(int i = 0 ; i < n; i++){
            slidingRow(w, i);
        }
        
        for(int i = 0 ; i < compressedRowMap[0].length; i++){
            slidingColumn(h, i);
        }
        
        int max = -1;
        
        for(int i = 0 ; i < compressedMap.length; i++){
            for(int j = 0 ; j < compressedMap[0].length; j++){
                if(max < compressedMap[i][j]){
                    max = compressedMap[i][j];
                    answer = new int[]{i, j};
                }
            }
        }
        
        return answer;
    }
    
    
    public void init(int n, int m, int[][] drops, int h, int w){
        map = new int[n][m];
        compressedRowMap = new int[n][m - w + 1];
        compressedMap = new int[n - h + 1][m - w + 1];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = 500_001;
            }
        }
        
        for(int i = 0; i < drops.length; i++){
            int row = drops[i][0];
            int col = drops[i][1];
            
            map[row][col] = i;
        }
    }
    
    public void slidingColumn(int size, int col){
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        
        for(int i = 0 ; i < compressedRowMap.length; i++){
            //지나간 값 빼주기
            while(!q.isEmpty() && q.peekFirst() <= i - size){
                q.pollFirst();
            }
            
            //가능성이 없는 것들 빼주기
            while(!q.isEmpty() && compressedRowMap[q.peekLast()][col] > compressedRowMap[i][col]){
                q.pollLast();
            }
            
            q.addLast(i);
            
            if(i >= size - 1){
                compressedMap[i - size + 1][col] = compressedRowMap[q.peekFirst()][col];
            }
        }
    }
    
    public void slidingRow(int size, int row){
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        
        for(int i = 0 ; i < map[row].length; i++){
            //지나간 값 빼주기
            while(!q.isEmpty() && q.peekFirst() <= i - size){
                q.pollFirst();
            }
            
            //가능성이 없는 것들 빼주기
            while(!q.isEmpty() && map[row][q.peekLast()] > map[row][i]){
                q.pollLast();
            }
            
            q.addLast(i);
            
            if(i >= size - 1){
                compressedRowMap[row][i - size + 1] = map[row][q.peekFirst()];
            }
        }
    }
}