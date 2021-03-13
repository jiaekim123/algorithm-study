/*
    https://programmers.co.kr/learn/courses/30/lessons/72413
    [프로그래머스][카카오 2021 블라인드 테스트] 합승 택시 요금
    https://www.acmicpc.net/problem/11404
    [플로이드 와샬] 백준 문제
 */
package week15;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] cost = new int[n + 1][n + 1];
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                cost[i][j] = 20000000; //200(최대 n) * 100000(최대 요금)
            }
            cost[i][i] = 0;
        }

        for(int i = 0; i < fares.length; i++) {
            cost[fares[i][0]][fares[i][1]] = fares[i][2];
            cost[fares[i][1]][fares[i][0]] = fares[i][2];
        }

        for(int k = 1; k < n + 1; k++) {
            for(int i = 1; i < n + 1; i++) {
                for(int j = 1; j < n + 1; j++) {
                    // i에서 j로 바로 가는 비용보다 k를 경유해서 가는 비용이 더 저렴하면 갱신
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < n + 1; i++) {
            // 출발지점(s)에서 합승지점(i) + a혼자 가는 비용 + b혼자 가는 비용과 비교해서 더 저렴한 것으로 갱신
            min = Math.min(min, cost[s][i] + cost[i][a] + cost[i][b]);
        }
        return min;
    }
}
