import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        PriorityQueue<String> pq = new PriorityQueue<>(
            (s1, s2) -> (s2 + s1).compareTo(s1 + s2)
        );

        for (int num : numbers) {
            pq.add(String.valueOf(num));
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        
        if (sb.charAt(0) == '0')
            return "0";

        return sb.toString();
    }
}