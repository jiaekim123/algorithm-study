/*
  https://programmers.co.kr/learn/courses/30/lessons/68937
  [프로그래머스][월간 코드 챌린지 시즌1] 트리 트리오 중간 값
 */
package week1;

import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        List <Integer> [] list = getList(n, edges);
        return getMax(list, n);
    }
    private List<Integer>[] getList(int n, int[][] edges){
        List <Integer> [] list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for(int [] edge : edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }
        return list;
    }

    /*
     서로 멀리 떨어진 지점의 거리(max) 값을 기준으로
     max의 값의 갯수가 2개 이상일 때는 중간값이 max로 리턴되고
     1개 이하이면 max-1을 리턴
     */
    private int getMax(List<Integer>[] list, int n){
        // 임의의 노드 1로부터 가장 먼 노드 A를 찾는다.
        int [] result = bfs(list, 1, n);
        int s = 1, max = 0, cnt = 0;
        for (int i = 2; i <= n; i++) {
            if(result[i] > result[s]) s = i;
        }
        // A로부터 각 노드까지의 거리를 찾는다.
        result = bfs(list, s, n);
        s = 1;
        for (int i = 1; i <= n; i++) {
            if(result[i] > result[s]) s = i;
        }
        for(int i : result) max = Math.max(max, i);
        for(int i : result) if(max == i) cnt++;
        // 이 때 가장 먼 거리의 노드가 여러개라면 A노드와 먼 노드 중 2개를 선택하면 되므로 가장 먼 거리 리턴
        if(cnt >= 2) return max;
        // 가장 먼 거리의 노드가 B 하나라면 다시 B 를 기준으로 탐색
        max = 0; cnt = 0;
        result = bfs(list, s, n);
        for(int i : result) max = Math.max(max, i);
        for(int i : result) if(max==i) cnt++;
        // 이 때 역시 가장 먼 거리의 노드가 여러개라면 B노드와 먼 노드 중 2개를 선택하면 되므로 가장 먼 거리 리턴
        // 가장 먼 거리의 노드가 A 하나라면 A와 B의 거리(트리의 지름)-1을 리턴
        if(cnt >= 2) return max;
        return max-1;
    }

    // edge 에서 각 edge 사이의 거리를 리턴
    private static int[] bfs(List<Integer>[] list, int edge, int n) {
        boolean[] visit = new boolean[n + 1];
        int[] distance = new int[n + 1];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(edge);
        visit[edge] = true;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            for (int i : list[num]) {
                if (i == num || visit[i]) continue;
                visit[i] = true;
                queue.add(i);
                distance[i] = distance[num] + 1;
            }
        }
        return distance;
    }
}