/*
    https://programmers.co.kr/learn/courses/30/lessons/72415
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 카드 짝 맞추기
 */
package week14;

import java.util.*;

class Solution {

    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};
    static int INFINIFY = 999999999;

    class Node implements Comparable<Node> {
        int d, x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int d, int x, int y) {
            this.d = d;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    public boolean isFinished(int[][] board) {
        for (int[] i : board) {
            for (int j : i) {
                if (j != 0) return false;
            }
        }
        return true;
    }

    public boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 4 && y < 4;
    }

    public int getDiks(int[][] board, int x1, int y1, int x2, int y2) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, x1, y1));
        int[][] dis = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dis[i][j] = INFINIFY;
            }
        }
        dis[x1][y1] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int d = now.d;
            if (dis[now.x][now.y] < d) continue;
            if (now.x == x2 && now.y == y2) return d;
            for (int i = 0; i < 4; i++) {
                int cnt = 0;
                int nx = now.x;
                int ny = now.y;
                // 한 칸씩 i 방향으로 옮겨가며 최단거리 계산
                while (inRange(nx + dx[i], ny + dy[i])) {
                    cnt++;
                    nx += dx[i];
                    ny += dy[i];
                    if(board[nx][ny] !=0 ) break; // 카드 마주
                    if (dis[nx][ny] > d + cnt) {
                        dis[nx][ny] = d + cnt;
                        pq.add(new Node(d + cnt, nx, ny));
                    }
                }

                // 카드 또는 벽을 마주친 경우
                if (dis[nx][ny] > d + 1) {
                    dis[nx][ny] = d + 1;
                    pq.add(new Node(d + 1, nx, ny));
                }
            }
        }
        return 0;
    }

    public int solve(int[][] board, int r, int c) {
        if (isFinished(board)) return 0;
        int answer = INFINIFY;

        // 카드 뒤집기
        for (int k = 1; k <= 6; k++) {
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == k) {
                        list.add(new Node(i, j));
                    }
                }
            }
            if (list.isEmpty()) continue;

            // 첫번째 카드 뒤집음
            int card1 = getDiks(board, r, c, list.get(0).x, list.get(0).y)
                    + getDiks(board, list.get(0).x, list.get(0).y, list.get(1).x, list.get(1).y) + 2;
            // 두번째 카드
            int card2 = getDiks(board, r, c, list.get(1).x, list.get(1).y)
                    + getDiks(board, list.get(1).x, list.get(1).y, list.get(0).x, list.get(0).y) + 2;

            board[list.get(0).x][list.get(0).y] = 0;
            board[list.get(1).x][list.get(1).y] = 0;

            answer = Math.min(answer,
                    Math.min(card1 + solve(board, list.get(1).x, list.get(1).y), card2 + solve(board, list.get(0).x, list.get(0).y)));

            board[list.get(0).x][list.get(0).y] = k;
            board[list.get(1).x][list.get(1).y] = k;

        }

        return answer;
    }

    public int solution(int[][] board, int r, int c) {
        return solve(board, r, c);
    }
}