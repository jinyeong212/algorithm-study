class Solution {
    int[] root;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        root = new int[n];
        
        for(int i = 0 ; i < n; i++)
            root[i] = i;
        
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(computers[i][j] == 1)
                    union(i,j);
            }
        }
        
        boolean[] visited = new boolean[n];
        
        for(int i = 0 ; i < n; i++){
            int temp = find(i);

            if(visited[temp])
                continue;
            
            visited[temp] = true;
            answer++;
        }
        
        return answer;
    }
    
    public int find(int n){
        if(root[n] == n)
            return n;
        
        return root[n] = find(root[n]);
    }
    
    public boolean union(int n1, int n2){
        int root1 = find(n1);
        int root2 = find(n2);
        
        if(root1 == root2)
            return false;
        
        root[root1] = root2;
        
        return true;
    }
}