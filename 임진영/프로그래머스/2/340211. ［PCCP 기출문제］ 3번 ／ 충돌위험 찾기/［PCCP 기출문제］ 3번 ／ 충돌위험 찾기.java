import java.util.*;

class Solution {
    int[][] points;
    int[][] routes;
    Queue<int[]> q;
    int R, C;
    HashMap<String, Integer> status;
    
    public int solution(int[][] points1, int[][] routes1) {
        init(points1, routes1);
        
        return run();
    }
    
    public int run(){
        int result = 0;
        
        while(!q.isEmpty()){
            result += isCollision();
            
            int size = q.size();
            
            for(int i = 0 ; i < size; i++){
                int[] cur = q.poll();
                
                if(routes[cur[2]].length <= cur[3])
                    cur[3]--;
                int[] destination = points[routes[cur[2]][cur[3]] - 1];
                
                String key = getKey(cur[0], cur[1]);
                int cnt = status.get(key);
                
                if(cnt - 1 == 0)
                    status.remove(key);
                else
                    status.put(key, cnt - 1);
                
                //r 좌표가 다른 경우
                if(cur[0] != destination[0]){
                    if(cur[0] > destination[0]){
                        cur[0]--;
                    }else{
                        cur[0]++;
                    }
                    
                    String newKey = getKey(cur[0], cur[1]);
                    
                    if(status.get(newKey) == null)
                        status.put(newKey, 0);
            
                    status.put(newKey, status.get(newKey) + 1);
                    
                    if(cur[0] == destination[0] && cur[1] == destination[1]){
                        cur[3]++;


                    }
                    
                    q.add(cur);
                }
                //c 좌표가 다른 경우
                else if(cur[1] != destination[1]){
                    if(cur[1] > destination[1]){
                        cur[1]--;
                    }else{
                        cur[1]++;
                    }
                    
                    String newKey = getKey(cur[0], cur[1]);
                    
                    if(status.get(newKey) == null)
                        status.put(newKey, 0);
            
                    status.put(newKey, status.get(newKey) + 1);
                    if(cur[0] == destination[0] && cur[1] == destination[1]){
                        cur[3]++;
                    }
                    
                    q.add(cur);
                }
            }
        }
        
        
        return result;
    }
    
    public String getKey(int r, int c){
        return r + " " + c;
    }
    
    public int isCollision(){
        int cnt = 0;
        
        for(String key : status.keySet()){
            if(status.get(key) != 1)
                cnt++;
        }
        
        return cnt;
    }
    
    public void init(int[][] points1, int[][] routes1){
        points = points1;
        routes = routes1;
        
        q = new ArrayDeque<int[]>();
        status = new HashMap<String, Integer>();
        
        for(int i = 0 ; i < routes.length; i++){
            int num = routes[i][0];
            
            int r = points[num - 1][0];
            int c = points[num - 1][1];
            q.add(new int[] {r, c, i, 1});
            
            String key = r + " " + c;
            
            if(status.get(key) == null)
                status.put(key, 0);
            
            status.put(key, status.get(key) + 1);
        }
    }
    
}