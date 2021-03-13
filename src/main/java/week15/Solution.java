package week15;

import java.util.Arrays;


//https://programmers.co.kr/learn/courses/30/lessons/72413
class Solution {
    // 1, n 번에서 A, B 까지 최단거리를 구한다.
    // 2. 처음 S 에서 n 번 까지의 거리를 구한다.
    // 3. S -> n 거리와 n->A,B 거리 각각을 더해서 최솟값을 찾는다.
    int MAX_COST = 1000000;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dis = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dis[i], MAX_COST);
            dis[i][i] = 0;
        }

        for (int[] f : fares) {
            dis[f[0]][f[1]] = f[2];
            dis[f[1]][f[0]] = f[2];
        }

        int answer = 999999999;

        // 각 지점에서 최소 거리 찾기 -> 플로이드 워샬로 풀기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    dis[j][k] = Math.min(dis[j][k], dis[j][i] + dis[i][k]); // 순서 중요
                }
            }
        }

        // 어디서 분기해야(각자 타야) 최솟값이 되는 지 찾기
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, dis[s][i] + dis[i][a] + dis[i][b]);
        }

        return answer;
    }

}
