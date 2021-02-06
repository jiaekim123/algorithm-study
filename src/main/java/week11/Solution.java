/*
    https://programmers.co.kr/learn/courses/30/lessons/72412
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 순위 검색
 */
package week11;

import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        // 1. info를 순회하면서 문자열 파싱 후 모든 경우의 수에 점수를 넣는다
        Map<String, List<Integer>> infos = new HashMap<>();
        for (int i = 0; i < info.length; i++) {
            String[] s = info[i].split(" ");
            Integer value = Integer.parseInt(s[4]);
            make(s, 0, "", infos);
        }

        // 2.모든 map의 key에 대해서 순회하면서 오름차순 정렬
        for(Map.Entry<String, List<Integer>> e : infos.entrySet()){
            Collections.sort(e.getValue());
        }

        int[] answer = new int[query.length];

        // 3. query 를 순회하면서 query정보 문자열 파싱
        for (int i = 0; i < query.length; i++) {
            String[] s = query[i].replace("and ", "").split(" ");
            int score = Integer.parseInt(s[4]);
            String key = s[0] + s[1] + s[2] + s[3];
            if(! infos.containsKey(key)) continue;
            List<Integer> scores = infos.get(key);

            answer[i] = scores.size() - upper_bound(scores, score);
        }
        return answer;
    }

    public int upper_bound(List<Integer> scores, int target) {
        int left = 0;
        int right = scores.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (scores.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public void make(String[] arr, int start, String res, Map<String, List<Integer>> map) {
        if (start == 4) {
//            System.out.println(res);
            int score = Integer.parseInt(arr[4]);
            if(map.containsKey(res)) map.get(res).add(score);
            else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(score);
                map.put(res, list);
            }
            return;
        }
        make(arr, start + 1, res + "-", map);
        make(arr, start + 1, res + arr[start], map);

    }
}