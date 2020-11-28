/*
  https://programmers.co.kr/learn/courses/30/lessons/49191
  [프로그래머스][그래프] 순위
 */
package week3;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
class Solution {

    public int solution(int n, int[][] results) {
        int answer = 0;
        int maxV = 1000000;
        int [][]map = new int [n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                map[i][j] = maxV;
            }
        }
        for(int []e : results){
            map[e[0]][e[1]] = 1;
        }
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        for(int i=1; i<=n; i++){
            boolean f = true;
            for(int j=1; j<=n ; j++){
                if(i==j) continue;
                if(map[i][j] == maxV && map[j][i] == maxV){
                    f = false;
                    break;
                }
            }
            if(f) answer++;
        }
        return answer;
    }
}