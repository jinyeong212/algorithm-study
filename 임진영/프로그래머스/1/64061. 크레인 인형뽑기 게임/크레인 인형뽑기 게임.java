import java.util.*;
/*
    board 각 칸에서 제일 윗 칸을 찾아주는 배열 필요함
*/

class Solution {
    int[] peek;
    int R, C;
    ArrayDeque<Integer> stack;
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        init(board);
        
        return simulation(board, moves);
    }
    
    public int simulation(int[][] board, int[] moves){
        int count = 0;
        
        for(int i : moves){
            int c = i - 1; 
            int r = peek[c];
            
            if(r == R)
                continue;
            
            //System.out.println("[r] : " + r + "[c] : " + c);
            
            int pick = board[r][c];
            
            if(!stack.isEmpty() && pick == stack.peekLast()){
                count += 2;
                stack.pollLast();
            }else{
                stack.add(pick);
            }
            
            peek[c]++;
        }
        
        return count;
    }
    
    public void init(int[][] board){
        R = board.length;
        C = board[0].length;
        
        stack = new ArrayDeque<>();
        peek = new int [C];
        
        for(int i = 0; i < C; i++){
            for(int j = 0; j < R; j++){
                peek[i] = R;
                if(board[j][i] != 0){
                    peek[i] = j;
                    break;
                }
            }
        }
    }
}