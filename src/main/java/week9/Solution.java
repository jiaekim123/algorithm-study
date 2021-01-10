/*
    https://programmers.co.kr/learn/courses/30/lessons/49189
    [프로그래머스][그래프] 가장 먼 노드
 */
package week9;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        LinkedList<Integer> [] list = new LinkedList[n+1];
        for(int i=0; i<=n; i++) list[i] = new LinkedList<>();
        for(int[] e: edge){
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }

        int []dis = new int [n+1];
        Arrays.fill(dis, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dis[1]=0;
        int max = 0;
        while (!q.isEmpty()){
            int now = q.poll();
            for(int next : list[now]){
                if(dis[next]!=-1)continue;
                dis[next]=dis[now]+1;
                max = Math.max(dis[next], max);
                q.add(next);
            }
        }
        for(int i=1;i<=n; i++){
            if(max == dis[i]) answer++;
        }
        return answer;
    }
}