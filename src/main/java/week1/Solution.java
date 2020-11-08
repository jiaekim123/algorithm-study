/*
  https://programmers.co.kr/learn/courses/30/lessons/68937
  [프로그래머스][월간 코드 챌린지 시즌1] 트리 트리오 중간 값
 */
package week1;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // 1. 중복 제외 임의의 3개를 뽑는다
    // 2. 거리를 구해서 최댓값을 리턴한다
    static LinkedList<Integer> [] list;
    static int max;
    public int solution(int n, int[][] edges) {
        list = new LinkedList[n+1];
        for(int i=1; i<=n; i++) list[i] = new LinkedList<>();
        for(int i=0; i<n-1; i++){
            int s = edges[i][0];
            int e = edges[i][1];
            list[s].add(e);
            list[e].add(s);
        }
        boolean []check = new boolean[n+1];
        max = 0;
//        solve(0,1,n, check);
        for(int i=1; i<n; i++){

        }
        return max;
    }

    static int []res = new int [3];
    public void solve(int idx, int start, int n, boolean []check){
        if(idx==3) {
//            for(int i=0; i<3; i++)
//            System.out.print(res[i]+" ");
//            System.out.println();
            int ab = bfs(res[0],res[1]);
            int bc = bfs(res[1],res[2]);
            int ca = bfs(res[2],res[0]);
            int r= (ab+bc+ca)/3;
//            System.out.println(ab+" "+bc+" "+ca+" "+r);
            if(max<r)max=r;
            return;
        }
        for(int i=start; i<=n; i++){
            if(check[i]) continue;
            res[idx] = i;
            check[i] = true;
            solve(idx+1, start+1, n, check);
            check[i] = false;
        }
    }
    public int bfs(int start, int end){
        boolean []c= new boolean[list.length];
        c[start]=true;
        Queue<Integer> q= new LinkedList<>();
        Queue<Integer> d= new LinkedList<>();
        q.add(start);d.add(0);
        int dis = 0;
        while(!q.isEmpty()){
            int now =q.poll();
            dis =d.poll();
            if(now == end) {
                break;
            }
            for(int next : list[now]){
                if(c[next])continue;
                q.add(next);
                d.add(dis+1);
                c[next]=true;
            }
        }
        return dis;
    }
}