/*
    https://programmers.co.kr/learn/courses/30/lessons/42898
    [프로그래머스][동적계획법] 등굣길
 */
package week7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Solution {
    private final static int DIV_NUM = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
//        int answer = Integer.MAX_VALUE;
//        Map<Integer, Integer> map = new HashMap<>();
//        // map의 최소 key값의 value를 반환
//
//        bfs(map, m, n, puddles);
//
//        for (int key : map.keySet()){
//            if (key < answer) answer = map.get(key);
//        }

        return dp(m, n, puddles);
    }
    private int dp(int m, int n, int[][] puddles) {
        int[][] paths = new int[m][n];
        paths[0][0] = 1;
        // 웅덩이는 -1로 변경
        for (int[] puddle : puddles){
            paths[puddle[0] - 1][puddle[1] - 1] = -1;
        }

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 웅덩이면 빠져나오고 나중 계산을 위해 0으로 변경
                if (paths[x][y] == -1){
                    paths[x][y] = 0;
                    continue;
                }
                // 왼쪽에서 온 루트 추가
                if (x != 0) {
                    paths[x][y] += paths[x-1][y] % DIV_NUM;
                }

                // 위에서 온 루트 추가
                if (y != 0) {
                    paths[x][y] += paths[x][y-1] % DIV_NUM;
                }
            }
        }

        return paths[m-1][n-1] % DIV_NUM;
    }

    private void bfs(Map<Integer, Integer> map, int m, int n, int[][] puddles){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0,0, 0));

        while (!queue.isEmpty()){
            Point point = queue.poll();
            for (Direction direction : Direction.values()) {
                // 다음 좌표를 구한다.
                int nextX = point.x + direction.x;
                int nextY = point.y + direction.y;
                // 학교에 도착하면 map에 경로의 길이를 넣는다.
                if (nextX == m-1 && nextY == n-1) {
                    if (map.containsKey(point.distance + 1)){
                        map.replace(point.distance + 1, map.get(point.distance + 1) + 1);
                    } else {
                        map.put(point.distance + 1, 1);
                    }
                }
                // 다음 좌표가 맵에 포함되었고 puddle값이 아닌지 확인한다. 갈 수 있는 길일 경우 queue에 넣는다.
                if (nextX < m && nextX>= 0 && nextY < n && nextY >= 0 && !isPuddle(nextX, nextY, puddles)) {
                    queue.offer(new Point(point.x + direction.x , point.y + direction.y, point.distance + 1));
                }
            }
        }

    }
    private boolean isPuddle(int x, int y, int[][] puddles){
        for (int[] puddle : puddles){
            if (x == puddle[0]-1 && y == puddle[1]-1) return true;
        }
        return false;
    }
}

class Point {
    int x;
    int y;
    int distance;

    public Point (int x, int y, int distance){
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

enum Direction {
    RIGHT (1, 0),
    DOWN (0, 1);

    int x; int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
}