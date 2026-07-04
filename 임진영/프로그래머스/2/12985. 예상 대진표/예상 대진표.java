class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        while(true){
            a++;
            b++;
            
            a /= 2;
            b /= 2;
            answer++;
            if(a == b)
                break;
        }

        return answer;
    }
}