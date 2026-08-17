import java.util.*;

/*
    5^8 약 39만
*/

class Solution {
    List<Set<Integer>> nums;
    
    public int solution(int N, int number) {       
        nums = new ArrayList<Set<Integer>>();
        
        for(int i = 0; i <= 8; i++){
            nums.add(new HashSet<Integer>());
            int num = 0;
            
            for(int j = 0; j < i; j++){
                num = num * 10 + N;
            }  
            
            nums.get(i).add(num);
        }
        
        for(int i = 1 ; i <= 8; i++){
            for(int j = 1; j < i; j++){
                int k = i - j;
                Set set = nums.get(i);
                for(int num1 : nums.get(j)){
                    for(int num2 : nums.get(k)){
                        set.add(num1 + num2);
                        set.add(num1 - num2);
                        set.add(num1 * num2);
                        if(num2 == 0)
                            continue;
                        
                        set.add(num1 / num2);
                    }
                }
            }
        }
        
        for(int i = 1; i <= 8; i++){
            Set<Integer> s = nums.get(i);
            for(int num : s){
                if(number == num)
                    return i;
            }
        }
        
        return -1;
    }
    
    
}