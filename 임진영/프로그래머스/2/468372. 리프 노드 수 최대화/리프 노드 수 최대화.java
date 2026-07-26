class Solution {
    public int solution(int dist_limit, int split_limit) {
        long answer = 1;

        for (long i = 1; i <= split_limit; i *= 2) {
            for (long j = 1; i * j <= split_limit; j *= 3) {

                // h2개의 2분배 층에 들어갈 수 있는 분배 노드 수
                long capacity2 = i - 1;

                // 실제 사용한 2분배 노드 수
                long used2 = Math.min(capacity2, (long) dist_limit);

                // 실제 사용량을 빼야 함
                long remains = dist_limit - used2;

                // h3개의 3분배 층에 들어갈 수 있는 분배 노드 수
                long capacity3 = i * (j - 1) / 2;

                // 실제 사용한 3분배 노드 수
                long used3 = Math.min(capacity3, remains);

                long leafCount = 1 + used2 + 2 * used3;

                answer = Math.max(answer, leafCount);
            }
        }

        return (int) answer;
    }
}