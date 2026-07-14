class Solution {
    static int maxMin;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int curTime = converToTime(pos);
        int opStart = converToTime(op_start);
        int opEnd   = converToTime(op_end);
        int endTime = converToTime(video_len);
        
        if(curTime <= opEnd && curTime >= opStart)
                curTime = opEnd;
        
        for(String command : commands){
            if(command.equals("prev"))
                curTime -= 10;
            else{
                curTime += 10;
            }
            
            
            curTime = Math.max(curTime, 0);
            curTime = Math.min(curTime, endTime);
            
            if(curTime <= opEnd && curTime >= opStart)
                    curTime = opEnd;
        }
        
        int min = curTime / 60;
        int sec = curTime % 60;
        
        String MIN = min < 10 ? "0" + min : ""+ min;
        String SEC = sec < 10 ? "0" + sec : ""+ sec;
        
        return MIN + ":" + SEC;
    }
    
    
    public int converToTime(String pos){
        int time = 0;
        
        time += (pos.charAt(0) - '0') * 10 * 60;
        time += (pos.charAt(1) - '0') * 60;
        time += (pos.charAt(3) - '0') * 10;
        time += pos.charAt(4) - '0';
        
        return time;
    }
}