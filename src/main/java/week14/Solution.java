/*
    https://programmers.co.kr/learn/courses/30/lessons/72415
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 카드 짝 맞추기
 */
package week14;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    // 1. bfs 로 특정위치에서 가까운 동일 숫자 찾기
    // 2. bfs 로 주변 숫자 중 가까운 숫자 찾기
    public int solution(int[][] board, int r, int c) {
        int answer = bfs(board, r, c, board[r][c]);
        return answer;
    }

    public int bfs(int[][] board, int sx, int sy, int num) {
        int maxLen = board.length;
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        boolean[][] check = new boolean[maxLen][maxLen];
        check[sx][sy] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(sx);
        q.add(sy);
        board[sx][sy] = 0;
        int[][] dis = new int[maxLen][maxLen];
        dis[sx][sy] = 1;
        int ret = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            if (board[x][y] == num) {
                board[x][y] = 0;
                int[] next = nextNum(board, x, y);
//                System.out.println("ret " + dis[x][y] + " next 숫자 " + board[next[0]][next[1]] + " ");
                ret = dis[x][y] + 1;
                int nextNum = board[next[0]][next[1]];
                if (nextNum != 0) {
                    ret += next[2] + bfs(board, next[0], next[1], nextNum);
                }
                break;
            }
            for (int i = 0; i < 4; i++) {
                // Ctrl + 이동
                for (int j = 1; j < maxLen; j++) {
                    int nx = x + dx[i] * j;
                    int ny = y + dy[i] * j;

                    if (nx < 0 || ny < 0 || nx >= maxLen || ny >= maxLen) continue;
                    if (check[nx][ny] || (board[nx][ny] != 0 && board[nx][ny] != num)) continue;

                    q.add(nx);
                    q.add(ny);
                    check[nx][ny] = true;
                    dis[nx][ny] = dis[x][y] + 1;
                }
            }
        }

        return ret;
    }

    public int[] nextNum(int[][] board, int sx, int sy) {
        int maxLen = board.length;
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        boolean[][] check = new boolean[maxLen][maxLen];
        check[sx][sy] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(sx);
        q.add(sy);
        int[] ret = new int[3];
        int[][] dis = new int[maxLen][maxLen];
        dis[sx][sy] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            if (board[x][y] != 0) {
                ret[0] = x;
                ret[1] = y;
                ret[2] = dis[x][y];
                return ret;
            }
            for (int i = 0; i < 4; i++) {
                // Ctrl + 이동
                for (int j = 1; j < maxLen; j++) {
                    int nx = x + dx[i] * j;
                    int ny = y + dy[i] * j;

                    if (nx < 0 || ny < 0 || nx >= maxLen || ny >= maxLen) continue;
                    if (check[nx][ny]) continue;
                    q.add(nx);
                    q.add(ny);
                    check[nx][ny] = true;
                    dis[nx][ny] = dis[x][y] + 1;
                }
            }
        }
        return ret;
    }
}