import java.util.*;

class Solution {
    class MyString{
        ArrayList<Character> value;
        
        MyString(String original){
            value = new ArrayList<Character>();
            
            String lowerCase = original.toLowerCase();
            
            for(int i = 0; i < lowerCase.length(); i++){
                value.add(lowerCase.charAt(i)); 
            }
            
            remove();
            convertPoint();
            removeFirstOrLastPoint();
            isEmpty();
            subString();
            addValue();
        }
        
        public void remove(){
            for(int i = value.size() - 1; i >= 0; i--){
                char c = value.get(i);
                
                if(c == '.')
                    continue;
                else if(c == '-')
                    continue;
                else if(c == '_')
                    continue;
                else if(c >= '0' && c <='9')
                    continue;
                else if(c >= 'a' && c <='z')
                    continue;
                
                value.remove(i);
            }
        }
        
        public void convertPoint(){
            for(int i = 0; i < value.size(); i++){
                char c = value.get(i);
                
                if(c == '.'){
                    while(i + 1 < value.size() && value.get(i + 1) == '.'){
                        value.remove(i + 1);
                    }
                }
            }
        }
        
        public void removeFirstOrLastPoint(){
            int size = value.size();
            
            if(size == 0)
                return;
            
            if(value.get(0) == '.')
                value.remove(0);
            
            size = value.size();
            
            if(size == 0)
                return;
            
             if(value.get(size - 1) == '.')
                value.remove(size - 1);
        }
        
        public void isEmpty(){
            if(value.isEmpty())
                value.add('a');
        }
        
        public void subString(){
            if(value.size() < 16)
                return;
            
            ArrayList<Character> nValue = new ArrayList<Character>();
            
            for(int i = 0; i < 15; i++){
                nValue.add(value.get(i));
            }
            
            if(nValue.get(14) == '.')
                nValue.remove(14);
            
            this.value = nValue;
        }
        
        public void addValue(){
            if(value.size() >= 3)
                return;
            
            while(value.size() < 3)
                value.add(value.get(value.size() - 1));
        }
        
        public String toString(){
            char[] arr = new char[value.size()];
            
            for(int i = 0; i < value.size(); i++){
                arr[i] = value.get(i);
            }
            
            return new String(arr);
        }
    }
    
    public String solution(String new_id) {
        MyString str = new MyString(new_id);
        
        String answer = str.toString();
        return answer;
    }
}