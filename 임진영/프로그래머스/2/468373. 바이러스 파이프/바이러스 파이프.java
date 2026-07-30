import java.util.*;

/*
    파이프는 a,b,c 3개의 종류 중 하나로 초기는 모두 닫힘
    
    배양체 중 하나만 감염, 열린 파이프를 통해 다른 배양체 감염 가능
    
    종류가 같다면 한꺼번에 열었다가 닫음
    
    완탐 시간 복잡도 3^k * n 
*/

class Solution {
    List<List<List<Integer>>> tree; 
    int[] direction;
    int max;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        max = 0;
          
        init(n, edges);
        
        Set<Integer> set = new HashSet<>();
        set.add(infection);
        
        dfs(0, k, set);
        
        return max;
    }
    
    public void dfs(int depth, int k, Set<Integer> set) {
    if (depth == k) {
        max = Math.max(max, set.size());
        return;
    }

    for (int i = 1; i <= 3; i++) {
        List<Integer> added = new ArrayList<>();
        Set<Integer> visited = new HashSet<>(set);
        Queue<Integer> q = new ArrayDeque<>();

        // 현재 선택된 모든 노드에서 동시에 탐색 시작
        q.addAll(set);

        while (!q.isEmpty()) {
            int now = q.poll();

            List<Integer> nextNodes = tree.get(i).get(now);

            if (nextNodes == null) {
                continue;
            }

            for (int next : nextNodes) {
                // add가 true면 처음 방문한 노드
                if (visited.add(next)) {
                    added.add(next);
                    q.add(next);
                }
            }
        }

        set.addAll(added);

        dfs(depth + 1, k, set);

        // 이번 단계에서 추가한 노드만 원복
        for (int num : added) {
            set.remove(num);
        }
    }
}
    
    public void init(int n, int[][] edges){
        tree = new ArrayList<List<List<Integer>>> ();
        direction = new int[n + 1];
        
        for(int i = 0; i <= 3; i++){
            tree.add(new ArrayList<List<Integer>>());
            for(int j = 0; j <= n; j++)
                tree.get(i).add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < edges.length; i++){
            int start = edges[i][0];
            int end   = edges[i][1];
            int type  = edges[i][2];
            
            tree.get(type).get(end).add(start);
            tree.get(type).get(start).add(end);
            
            direction[start]    = direction[start] | (1<<type);
            direction[end]      = direction[end] |(1<<type);
        }
    }
}