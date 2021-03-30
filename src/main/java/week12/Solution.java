/*
    https://programmers.co.kr/learn/courses/30/lessons/42884#
    [프로그래머스][탐욕법(Greedy)] 단속 카메라
 */
package week12;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        // 1. routes 진입 순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int left = routes[0][0];
        int right = routes[0][1];

        int answer = 1;

        for (int i = 1; i < routes.length; i++) {
            // 겹치지 않는 부분 cctv 추가 
            if( right < routes[i][0]){
                answer ++;
                left = routes[i][0];
                right = routes[i][1];
            }else {
                left = Math.min(routes[i][0], left);
                right = Math.min(routes[i][1], right);
            }
        }

        return answer;
    }
}