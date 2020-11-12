/*
  https://programmers.co.kr/learn/courses/30/lessons/68937
  [프로그래머스][월간 코드 챌린지 시즌1] 트리 트리오 중간 값
 */
package week1;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static LinkedList<Integer> [] list;
    static int []dis;
    static int x,max,cnt, a,b,c;

    public int solution(int n, int[][] edges) {
        list = new LinkedList[n+1];
        dis = new int[n+1];

        for(int i=0;i<=n; i++) list[i] = new LinkedList<>();

        for(int i=0; i<edges.length; i++){
            int s = edges[i][0];
            int e = edges[i][1];
            list[s].add(e);
            list[e].add(s);
        }
        x=max=0;
        dfs(1);  // 가장 깊은 점 찾기
        a = x;

        x=max=0;
        dis = new int[n+1];
        dfs(a);
        b = x;

        for(int i=1; i<=n; i++) {
            if(i!=a && dis[i] == max) cnt++;
        }

        if(cnt >=2) return max;
        else {
            x=max=0;
            dis = new int [n+1];
            dfs(b);
            c = x;
            cnt=0;
            for(int i=1; i<=n; i++){
                if(i!=b && dis[i] == max)cnt++;
            }
            if(cnt >=2) return max;
            return max-1;
        }
    }

    static void dfs(int start){
        if(max < dis[start]) {
            max = dis[start];
            x = start;
        }

        for(int next: list[start]){
            if(dis[next] == 0){
                dis[next] = dis[start]+1;
                dfs(next);
            }
        }
    }
}