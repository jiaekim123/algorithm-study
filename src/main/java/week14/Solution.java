/*
    https://programmers.co.kr/learn/courses/30/lessons/72415
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 카드 짝 맞추기
 */
package week14;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    private final int[] dy = new int[]{-1, 1, 0, 0};
    private final int[] dx = new int[]{0, 0, -1, 1};
    private final static int MAX_MAP_X = 4;
    private final static int MAX_MAP_Y = 4;
    private final static int CARD_COUNT = 6;
    public int solution(int[][] board, int r, int c) {
        int answer = backtracking(board, r, c);
        return answer;
    }

    // 최소 키보드 입력횟수
    private int dijkstra(int[][] board, Point start, Point end) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(start);
        int[][] distance = new int[MAX_MAP_Y][MAX_MAP_X];
        for (int i = 0; i < MAX_MAP_X; i++){
            for (int j = 0; j<MAX_MAP_Y; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[start.y][start.x] = 0;
        while (!queue.isEmpty()){
            Point now = queue.poll();
            int nowDist = now.distance;
            if (distance[now.y][now.x] < nowDist) continue;
            if (now.y == end.y && now.x == end.y) return nowDist;

            for (int i = 0; i<4; i++){
                int count = 0;
                int nowY = now.y;
                int nowX = now.x;

                // 한 칸씩 i방향으로 옮겨가면서 최단거리를 계산한다.
                while(isRange(nowX + dx[i], nowY + dy[i])){
                    count++;
                    nowY += dy[i];
                    nowX += dx[i];

                    if (board[nowY][nowX] != 0) break;

                    if (distance[nowY][nowX] > nowDist + count) {
                        distance[nowY][nowX] = nowDist + count;
                        queue.offer(new Point(nowDist + count, nowY, nowX));
                    }
                }

                // 카드 또는 벽을 마주친 경우 Ctrl을 이용해 이동
                if (distance[nowY][nowX] > nowDist + 1) {
                    distance[nowY][nowX] = nowDist + 1;
                    queue.offer(new Point(nowDist + 1, nowY, nowX));
                }
            }
        }
        return 0;
    }
    private boolean isRange(int x, int y){
        return y >= 0 && y < MAX_MAP_Y && x >= 0 && x < MAX_MAP_X;
    }
    private boolean isFinished(int[][] board){
        for (int[] v : board){
            for (int i : v){
                if (i != 0) return false;
            }
        }
        return true;
    }
    // 백트래킹으로 모든 종류의 카드 뒤집어보기
    private int backtracking(int[][] board, int y, int x) {
        if (isFinished(board)) return 0; // 전부 뒤집은 경우
        int answer = Integer.MAX_VALUE;
        // 카드 뒤집기
        for (int k = 1; k < CARD_COUNT; k++) {
            List<Point> point = new ArrayList<>();
            for (int i = 0; i < MAX_MAP_Y; i++){
                for (int j = 0; j< MAX_MAP_X; j++){
                    if (board[i][j] == k) point.add(new Point(i, j));
                }
            }
            if (point.isEmpty()) continue;

            // 앞에꺼를 먼저 뒤집음
            int card1 = dijkstra(board, new Point(y, x), new Point(point.get(0).y, point.get(0).x))
                    + dijkstra(board, new Point(point.get(0).y, point.get(0).x),
                    new Point(point.get(1).y, point.get(1).x)) + 2;

            // 뒤에꺼를 먼저 뒤집음
            int card2 = dijkstra(board, new Point(y, x), new Point(point.get(1).y, point.get(1).x))
                    + dijkstra(board, new Point(point.get(1).y, point.get(1).x),
                    new Point(point.get(0).y, point.get(0).x)) + 2;

            // dfs
            board[point.get(0).y][point.get(0).x] = 0;
            board[point.get(1).y][point.get(1).x] = 0;

            answer = Math.min(answer,
                    Math.min(card1 + backtracking(board, point.get(1).y, point.get(1).x),
                            card2 + backtracking(board, point.get(0).y, point.get(0).x)));
            board[point.get(0).y][point.get(0).x] = k;
            board[point.get(1).y][point.get(1).x] = k;
        }
        return answer;

    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int distance;
        Point (int distance, int y, int x) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
        Point (int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return this.distance - o.distance;
        }
    }
}