import java.util.*;

class Solution {
    public int solution(int distance, int[] rock, int n) {
        ArrayList<Integer> rocks = new ArrayList<Integer>();
        
        rocks.add(0);
        rocks.add(distance);
        
        for(int num : rock)
            rocks.add(num);
        
        Collections.sort(rocks);
        
        int l = 0;
        int r = distance;
        
        while(l < r){
            int mid = (l + r) / 2;
            
            int cnt = getCnt(mid, rocks);
            
            if(cnt > n)
                r = mid;
            else
                l = mid + 1;
        }
        
        return l;
    }
    
    public int getCnt(int mid, ArrayList<Integer> rocks){
        int cnt = 0;
        int now = 0;
        
        for(int i = 1; i < rocks.size(); i++){
            if(rocks.get(i) - rocks.get(now) <= mid)
                cnt++;
            else
                now = i;
        }
        
        return cnt;
    }
}