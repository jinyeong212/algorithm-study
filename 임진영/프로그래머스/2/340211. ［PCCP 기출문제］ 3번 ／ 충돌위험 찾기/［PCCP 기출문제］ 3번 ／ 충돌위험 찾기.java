import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        int robot = routes.length;
        List<int[]>[] route = new ArrayList[robot];

        // 각 로봇 이동 경로 저장
        int maxRoute = Integer.MIN_VALUE;
        for(int i = 0; i < robot; i++) {
            route[i] = new ArrayList<>();

            int start = routes[i][0];

            route[i].add(new int[] {points[start - 1][0], points[start - 1][1]});

            for(int j = 1; j < routes[i].length; j++) {
                int end = routes[i][j];
                int startR = points[start - 1][0];
                int startC = points[start - 1][1];
                int endR = points[end - 1][0];
                int endC = points[end - 1][1];

                int dr = endR - startR;
                int dc = endC - startC;

                int r = startR;
                int c = startC;
                while(true) {
                    if(dr < 0) {
                        route[i].add(new int[] {--r, c});
                        dr++;
                    } else if(dr > 0) {
                        route[i].add(new int[] {++r, c});
                        dr--;
                    } else {
                        if(dc < 0) {
                            route[i].add(new int[] {r, --c});
                            dc++;
                        } else if(dc > 0) {
                            route[i].add(new int[] {r, ++c});
                            dc--;
                        } else break;
                    }
                }

                start = end;
            }

            maxRoute = Math.max(maxRoute, route[i].size());
        }

        int time = 0;
        while(time < maxRoute) {
            int[][] visited = new int[101][101];

            for(int i = 0; i < robot; i++) {
                if(time < route[i].size()) {
                    int r = route[i].get(time)[0];
                    int c = route[i].get(time)[1];

                    if(visited[r][c] == 1) answer++;

                    visited[r][c]++;
                }
            }

            time++;
        }

        return answer;
    }
}