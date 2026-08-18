import java.util.*;
import java.io.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        int cnt = 0;
        
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        
        for(int i = 0; i < number.length(); i++){
            int temp = number.charAt(i) - '0';
            
            while(cnt < k && !stack.isEmpty()){
                if(temp <= stack.peekLast())
                    break;
                
                stack.pollLast();
                cnt++;
            }
            
            stack.add(temp);
        }
        
        while(cnt < k && !stack.isEmpty()){
            stack.pollLast();
            cnt++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!stack.isEmpty()){
            sb.append(stack.poll());
        }
        
        return sb.toString();
    }

}