import java.io.*;
import java.util.*;

class Solution {
    ArrayList<int[]> nums;
    ArrayList<Character> operators;
    ArrayList<int[]> guessNums;
    ArrayList<Character> guessOperators;
    
    //가능한 진법
    boolean[] numberalSystems;
    
    public String[] solution(String[] expressions)throws IOException {
        init(expressions);
        String[] answer = new String[guessNums.size()];
        getNumberalSystem();
        
        for(int i = 0; i < guessNums.size(); i++){
            boolean flag = true;
            int num1 = guessNums.get(i)[0];
            int num2 = guessNums.get(i)[1];
            int num3 = -1;
            
            for(int numberalSystem = 2; numberalSystem < 10; numberalSystem++){
                //이미 안되는 진법은 넘기기
                if(!numberalSystems[numberalSystem])
                    continue;
                
                int convertNum1 = convertNum(numberalSystem, num1);
                int convertNum2 = convertNum(numberalSystem, num2);
                int result = calculator(convertNum1, convertNum2, guessOperators.get(i));
                
                result = convertToNumberalSystemNum(result, numberalSystem);
                
                if(num3 != -1){
                    if(num3 != result){
                        flag = false;
                        break;
                    }  
                }
                 
                num3 = result;
            }
            
            if(flag)
                answer[i] = num1 + " " + guessOperators.get(i) + " " + num2 + " = " + num3; 
            else
                answer[i] = num1 + " " + guessOperators.get(i) + " " + num2 + " = ?";
        }
        
        return answer;
    }
    
    public int convertToNumberalSystemNum(int num, int numberalSystem){
        int result = 0;
        int cur = 1;
        int cnt = 0;
        
        while(cur * numberalSystem <= num){
            cur *= numberalSystem;
            cnt++;
        }
        
        while(cnt != -1){
            cnt--;
            result *= 10;
                
            if(num == 0)
                continue;
            result += num / cur;
            num %= cur;
            cur /= numberalSystem;
        }
        
        return result;
    }
    
    public void getNumberalSystem(){
        for(int i = 0 ; i < nums.size(); i++){
            for(int numberalSystem = 2; numberalSystem < 10; numberalSystem++){
                //이미 안되는 진법은 넘기기
                if(!numberalSystems[numberalSystem])
                    continue;
                
                int convertNum1 = convertNum(numberalSystem, nums.get(i)[0]);
                int convertNum2 = convertNum(numberalSystem, nums.get(i)[1]);
                int convertNum3 = convertNum(numberalSystem, nums.get(i)[2]);
                
                int result = calculator(convertNum1, convertNum2, operators.get(i));
                
                if(result != convertNum3)
                    numberalSystems[numberalSystem] = false;
            }   
        }
    }
    
    public int calculator(int num1, int num2, char command){
        if(command == '-')
            return num1 - num2;
        
        else
            return num1 + num2;
    }
    
    public int convertNum(int numberalSystem, int num){
        int result = 0;
        
        int cur = 1;
        
        //10진법으로 바꿔주기
        while(num != 0){
            int num1 = num % 10;
            
            result += num1 * cur;
            
            num /= 10;
            cur *= numberalSystem;
        }
        
        return result;
    }
    
    public void init(String[] expressions)throws IOException{
        nums = new ArrayList<int []>();
        operators = new ArrayList<Character>();
        guessNums = new ArrayList<int []>();
        guessOperators = new ArrayList<Character>();
        
        numberalSystems = new boolean [10];
        
        for(int num = 2; num <= 9; num++)
            numberalSystems[num] = true;
        
        for(int index = 0; index < expressions.length; index++){
            StringTokenizer st = new StringTokenizer(expressions[index]);
            
            int num1        = Integer.parseInt(st.nextToken());
            char operator   = st.nextToken().charAt(0);
            int num2        = Integer.parseInt(st.nextToken());
            st.nextToken();
            String num3     = st.nextToken();
            
            if(num3.equals("X")){
                guessNums.add(new int[] {num1, num2});
                guessOperators.add(operator);
            }else{
                nums.add(new int[]{num1, num2, Integer.parseInt(num3)});
                operators.add(operator);
            }
        }
        
        int max = 1;
        
        for(int[] num : nums){
            max = Math.max(max, getMax(num[0]));
            max = Math.max(max, getMax(num[1]));
            max = Math.max(max, getMax(num[2]));
        }
        for(int[] num : guessNums){
            max = Math.max(max, getMax(num[0]));
            max = Math.max(max, getMax(num[1]));
        }
        
        for(int i = 2; i <= max; i++){
            numberalSystems[i] = false;
        }
    }
    
    public int getMax(int num){
        int max = 1;
        
        while(num != 0){
            max = Math.max(max, num % 10);
            num /= 10;
        }
        
        return max;
    }
}