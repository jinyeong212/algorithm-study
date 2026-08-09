import java.util.HashSet;

public class Solution {
    static final int SIZE = 1_000_001;

    static int[] child = new int[SIZE];
    static int[] parent = new int[SIZE];
    static HashSet<Integer> forest = new HashSet<>();
    static int[][] type = new int[SIZE][2];

    public static int[] solution(int[] nodes, int[][] edges) {
        for (int node : nodes)
            parent[node] = node;

        for (int[] edge : edges) {
            int v1 = edge[0], v2 = edge[1];
            union(v1, v2);
            child[v1]++;
            child[v2]++;
        }

        for (int node : nodes) {
            int p = find(node);
            forest.add(p);
            type[p][isOdd(node) == isOdd(child[node]) ? 0 : 1]++;
        }

        int[] answer = {0, 0};
        for (int g : forest) {
            if (type[g][0] == 1)
                answer[0]++;
            if (type[g][1] == 1)
                answer[1]++;            
        }
        return answer;
    }

    private static void union(int v1, int v2) {
        parent[find(v2)] = find(v1);
    }

    private static int find(int v) {
        return parent[v] = (parent[v] == v ? v : find(parent[v]));
    }

    private static boolean isOdd(int i) {
        return i % 2 == 1;
    }    
}