package week15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//https://programmers.co.kr/learn/courses/30/lessons/72413
class Solution {
    // 1, n 번에서 A, B 까지 최단거리를 구한다.
    // 2. 처음 S 에서 n 번 까지의 거리를 구한다.
    // 3. S -> n 거리와 n->A,B 거리 각각을 더해서 최솟값을 찾는다.

    List<Point>[] list;
    int MAX_COST = 999999999;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) list[i] = new ArrayList<>();

        for (int[] f : fares) {
            list[f[0]].add(new Point(f[1], f[2]));
            list[f[1]].add(new Point(f[0], f[2]));
        }

        int answer = -1;

        // i 번에서 s 까지 가는 경로
        for (int i = 1; i <= n; i++) {
            int cost_S = distance(i, s);
            if(cost_S == MAX_COST) continue;
            System.out.println(i + "번에서 " + s + "까지 가는 택시비 " + cost_S);
            int cost_A = distance(i, a);
            int cost_B = distance(i, b);
            System.out.println(i + "번에서 " + a + "까지 가는 택시비 " + cost_A);
            System.out.println(i + "번에서 " + b + "까지 가는 택시비 " + cost_B);
            int total = cost_A + cost_B + cost_S;
            if (answer == -1 || answer > total) answer = total;
            System.out.println(total);
            System.out.println();
        }
        return answer;
    }

    // x 에서 b 로 가는 최단경로(금액)
    public int distance(int x, int y) {
        // s 에서 b 로 갈 때
        int len = list.length;
        int[] cost = new int[len];
        Arrays.fill(cost, MAX_COST);

        cost[x] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(x, 0));

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.number == y) break;
            for (Point np : list[p.number]) {
                if (cost[np.number] < cost[p.number] + np.cost) continue;
                cost[np.number] = cost[p.number] + np.cost;
                pq.add(np);
            }
        }

        return cost[y];
    }

    class Point implements Comparable<Point> {
        int number, cost;

        Point(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.cost - o.cost;
        }
    }
}
