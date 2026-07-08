import java.util.*;

class Solution {

    Map<String, Integer> checkedWords;
    List<Word> words;

    class Word {
        int start;
        int end;
        String value;
        boolean spoiled;

        Word(int start, int end, String value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[start] : " + start
                    + " [end] : " + end
                    + " [value] : " + value;
        }
    }

    public int solution(String message, int[][] spoilerRanges) {
        init(message);

        return getResult(spoilerRanges);
    }

    private int getResult(int[][] spoilerRanges) {
        int answer = 0;

        for (Word word : words) {
            for (int[] range : spoilerRanges) {
                int l = range[0];
                int r = range[1];

                // 두 구간이 겹치는지 확인
                if (word.start <= r && l <= word.end) {
                    word.spoiled = true;
                    break;
                }
            }
        }

        for (Word word : words) {
            if (!word.spoiled) {
                continue;
            }

            int count = checkedWords.get(word.value);

            if (count == 1) {
                answer++;
            }

            checkedWords.put(word.value, count - 1);
        }

        return answer;
    }

    private void init(String message) {
        checkedWords = new HashMap<>();
        words = new ArrayList<>();

        int index = 0;

        while (index < message.length()) {

            // 공백 건너뛰기
            if (message.charAt(index) == ' ') {
                index++;
                continue;
            }

            int start = index;

            while (index < message.length()
                    && message.charAt(index) != ' ') {
                index++;
            }

            int end = index - 1;
            String value = message.substring(start, index);

            words.add(new Word(start, end, value));
            checkedWords.put(
                    value,
                    checkedWords.getOrDefault(value, 0) + 1
            );
        }
    }
}