/*
    https://programmers.co.kr/learn/courses/30/lessons/70130
    [프로그래머스][월간 코드 챌린지 시즌1] 스타수열
 */
package week8;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>(a.length);

        for (int i = 0; i < a.length; i++) {
            if (map.get(a[i]) == null) {
                map.put(a[i], 1);
            } else {
                map.replace(a[i], map.get(a[i]) + 1);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) * 2 <= answer) continue;
            int star = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if ((a[j] == key || a[j + 1] == key) && (a[j] != a[j + 1])) {
                    star += 2;
                    j++;
                }
            }
            answer = Math.max(answer, star); // 스타 수열 최대값
        }
        return answer;
    }
}
