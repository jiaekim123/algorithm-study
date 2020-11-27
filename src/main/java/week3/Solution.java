/*
  https://programmers.co.kr/learn/courses/30/lessons/49191
  [프로그래머스][그래프] 순위
 */
package week3;

class Solution {
    public int solution(int n, int[][] results) {
        int INF = Integer.MAX_VALUE;
        int answer = 0;
        boolean arr[][] = new boolean[n][n];

        // a가 b에게 승리하거나 패배했으면 true로
        for (int i = 0; i < results.length; i++) {
            arr[results[i][0] - 1][results[i][1] - 1] = true;
        }

        // a가 b한테 이겼는데 b가 c한테 이겼으면 c는 a한테 진걸로 처리
        for (int i = 0; i < n; i++) { // 거쳐가는 꼭짓점
            for (int j = 0; j < n; j++) { // 출발하는 꼭짓점
                for (int k = 0; k < n; k++) { // 도착하는 꼭짓점
                    if (arr[j][i] && arr[i][k]) {
                        arr[j][k] = true;
                    }
                }
            }
        }

        // n-1번의 승패 결과가 있으면 결과를 알 수 있음.
        for (int i = 0; i < n; i++) {
            int result = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] || arr[j][i]) {
                    result++;
                }
            }
            if (result == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}