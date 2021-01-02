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
    public int solution(int m, int n, int[][] puddles) {
        int answer = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        // map의 최소 key값의 value를 반환

        bfs(map, m, n, puddles);

        for (int key : map.keySet()){
            if (key < answer) answer = map.get(key);
        }

        return answer;
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