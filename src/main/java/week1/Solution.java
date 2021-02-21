/*
  https://programmers.co.kr/learn/courses/30/lessons/68937
  [프로그래머스][월간 코드 챌린지 시즌1] 트리 트리오 중간 값
 */
package week1;

import java.util.LinkedList;

public class Solution {
// TODO : 해 결 해
    static LinkedList<Integer> [] list;
    static int []dis;
    static int cnt=0, max=0, leaf=1;

    public int solution(int n, int[][] edges) {
        list = new LinkedList[n+1];
        for(int i=0;i<=n; i++) list[i] = new LinkedList<>();
        dis = new int [n+1];
        for(int i=0; i<edges.length; i++){
            int s = edges[i][0];
            int e = edges[i][1];
            list[s].add(e);
            list[e].add(s);
        }

        solve(1,n, false);  // 임의의 정점 1에서 가장 깊은 리프노드를 구한다 (정점 A)
        solve(leaf, n, true); // 구한 리프노드 leaf 에서 (가장 긴) 다른 리프노드를 구한다  (정점 B)
        if(cnt>=2) return max; // 구한 리프노드 갯수가 2개 이상일 때 중간값의 최댓값은 트리의 지름이다
        else{ // 구한 리프노드 갯수가 1개 일 때 정점 C를 확인해야 한다
            solve(leaf, n, true);  // 리프노드를 구한다 (정점 C)
            if(cnt>=2) return max; // 구한 리프노트 C가 2개 이상이면 중간값의 최댓값은 트리의 지름이다
            else return max-1; // 정점이 1개만 나오면 중간값의 최댓값은 트리의 지름-1 이 된다.
        }
    }

    static void solve(int start, int n, boolean T){
        cnt = 0;
        max = 0;
        dis = new int [n+1];
        dfs(start, 0, T);
    }

    static void dfs(int cur, int len, boolean T){
        if(dis[cur]==1) return; // 방문한 노드면 return

        if(T == false){ // 리프노드가 아닐 때
            if(len> max){
                leaf = cur; // 리프노드 업데이트
                max = len;
            }
        }
        else{ // 리프노드 일 때
            if(len>max){ //max 가 업데이트 되면
                leaf = cur; // 리프노드 업데이트
                cnt = 1; // cnt 는 다시 1이 된다
                max = len;
            }
            else if(len == max) cnt ++; //len 과 처음구한 (max)값과 같을 때 갯수 증가
        }

        dis[cur] = 1; // 방문 체크
        for(int next : list[cur]){
            dfs(next, len+1, T);
        }
    }
}