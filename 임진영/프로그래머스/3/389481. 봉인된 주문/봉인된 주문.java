import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] banNums = new long[bans.length];

        for (int i = 0; i < bans.length; i++) {
            banNums[i] = convertToNum(bans[i]);
        }

        Arrays.sort(banNums);

        long target = n;

        for (long ban : banNums) {
            if (ban <= target) {
                target++;
            } else {
                break;
            }
        }

        return convertToString(target);
    }
    
    public long convertToNum(String str) {
        long result = 0;

        for (int i = 0; i < str.length(); i++) {
            result = result * 26 + (str.charAt(i) - 'a' + 1);
        }

        return result;
    }

    public String convertToString(long num) {
        num--;

        char now = (char) ('a' + num % 26);

        if (num < 26) {
            return String.valueOf(now);
        }

        return convertToString(num / 26) + now;
    }
}