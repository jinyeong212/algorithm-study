import java.util.*;

class Solution {
    int[] minDis;
    List<List<Integer>> graph;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        init(n, edge);
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{1, 0});
        minDis[1] = 0;
        
        while(!q.isEmpty()){
            int[] temp = q.poll();
            
            for(int node : graph.get(temp[0])){
                if(minDis[node] <= temp[1] + 1)
                    continue;
                
                minDis[node] = temp[1] + 1;
                q.add(new int[]{node, temp[1] + 1});
            }
        }
        
        int max = 0;
        
        for(int i = 2; i <= n; i++)
            max = Math.max(max, minDis[i]);
        
        for(int i = 2; i<= n; i++){
            if(minDis[i] == max)
                answer++;
        }
        
        return answer;
    }
    
    public void init(int n, int[][] edges){
        minDis = new int[n + 1];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        
        graph = new ArrayList<List<Integer>>();
        
        for(int i = 0 ; i < n+1; i++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
    }
}