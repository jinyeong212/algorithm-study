import java.util.*;

/*
    1. 숫자와 연산자를 배열로 나눈다.
*/

class Solution {
    List<Long>          nums;
    List<Character>     operators;
    char[]              priority = {'*', '+', '-'};
    
    public long solution(String expression) {
        long answer = 0;
        
        init(expression);
        
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(j == i)
                    continue;
                
                boolean isVisited[] = new boolean[3];
                isVisited[i] = true;
                isVisited[j] = true;
                
                for(int k = 0; k < 3; k++){
                    if(!isVisited[k]){
                        long result = excute(new int[]{i,j,k});
                        
                        answer = Math.max(answer, result < 0 ? (-1 * result) : result);
                    }
                }
            }
        }
        
        return answer;
    }
    
    public long excute(int[] orders){
        Queue<Long>         numQ    = new ArrayDeque<>();
        Queue<Character>    operQ   = new ArrayDeque<>();
        
        for(long num : nums)
            numQ.add(num);
        
        for(char operator : operators)
            operQ.add(operator);
        
        for(int idx = 0; idx < 3; idx++){
            int size = operQ.size();
            long temp = numQ.poll();;
            
            for(int cnt = 0; cnt < size; cnt++){
                char command = operQ.poll();
                
                //연산해주기
                //연산자 버리기
                //이후 계산을 위해 숫자 유지하기
                if(command == priority[orders[idx]]){
                    long temp2 = numQ.poll();
                    temp = calculator(command, temp, temp2);
                }else{
                    //연산x
                    //연잔자 다시 큐에 넣기
                    //숫자 넣어주기
                    operQ.add(command);
                    numQ.add(temp);
                    
                    temp = numQ.poll();
                }
            }
            
            //마지막 숫자 넣어주기
            numQ.add(temp);
        }
        
        return numQ.poll();
    }
    
    public long calculator(char command, long num1, long num2){
        if(command == '+')
            return num1 + num2;
        else if(command == '*')
            return num1 * num2;
        else
            return num1 - num2;
    }
    
    public void init(String expression){
        nums = new ArrayList<Long>();
        operators = new ArrayList<Character>();
        
        //숫자 시작점 찾기
        int start = 0;
        
        for(int idx = 0; idx <= expression.length(); idx++){
            if(idx == expression.length()){
                nums.add(Long.parseLong(expression.substring(start, idx)));
                break;
            }
            
            if(expression.charAt(idx) == '+' || 
               expression.charAt(idx) == '-' || 
               expression.charAt(idx) == '*'
              ){
                nums.add(Long.parseLong(expression.substring(start, idx)));
                operators.add(expression.charAt(idx));
                
                //다음 숫자 시작점
                start = idx + 1;
            }
            
        }
    }
}