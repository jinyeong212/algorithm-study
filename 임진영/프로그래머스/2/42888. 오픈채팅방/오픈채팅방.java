import java.io.*;
import java.util.*;

class Solution {
    HashMap<String, String> nicknames;
    List<Sentence> list;
    
    public String[] solution(String[] record) throws Exception{
        String[] answer = {};
        
        nicknames = new HashMap<String, String>();
        list = new ArrayList<Sentence>();
        for(String str : record){
            StringTokenizer st = new StringTokenizer(str);
            
            String command = st.nextToken();
            String uid = st.nextToken();
            
            if(command.equals("Enter")){
                list.add(new Sentence(uid, "님이 들어왔습니다."));
            }
            
            if(command.equals("Leave")){
                list.add(new Sentence(uid, "님이 나갔습니다."));
                continue;
            }
            
            String temp = st.nextToken();
            nicknames.put(uid, temp);
        }
        
        answer = new String[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i).toString();
        }
        
        return answer;
    }
    
    class Sentence{
        String uid;
        String content;
        
        Sentence(String uid, String content){
            this.uid        = uid;
            this.content    = content;
        }
        
        @Override
        public String toString(){
            return nicknames.get(uid) + content;
        }
    }
}