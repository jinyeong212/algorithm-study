import java.util.*;

class Solution {
    List<List<Integer>> tree;
    int dp[][];
    
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        
        init(links, sales);
        dfs(1, sales);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void init(int[][] links, int[] sales) {
        tree    = new ArrayList<List<Integer>>();
        dp      = new int [sales.length + 1][2];
        
        for(int i = 0; i <= sales.length + 1; i++){
            tree.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < links.length; i++){
            int parant = links[i][0];
            int child  = links[i][1];
            
            tree.get(parant).add(child);
        }
    }
    
    public void dfs(int leader, int[] sales){
        List<Integer> team = tree.get(leader);
        
        dp[leader][0] = sales[leader - 1];
        dp[leader][1] = 0;
        
        int temp = Integer.MAX_VALUE;
        boolean isEntry = false;
        
        for(int employee : team){
            dfs(employee, sales);
            
            int min = Math.min(dp[employee][0], dp[employee][1]);
            
            temp = Math.min(temp, dp[employee][0] - min);
            
            dp[leader][0] += min;
            dp[leader][1] += min;
            
            //자식 참여 여부를 체크 해주기
            if(min == dp[employee][0])
                isEntry = true;
        }
        
        //팀내에 참여자 없음
        if(!isEntry && team.size() > 0)
            dp[leader][1] += temp;
    }
}