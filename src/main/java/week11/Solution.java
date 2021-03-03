/*
    https://programmers.co.kr/learn/courses/30/lessons/72412
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 순위 검색
 */
package week11;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            String[] results = info[i].split(" ");
            list.add(new String[]{results[0], results[1], results[2], results[3], results[4]});
        }

        for (int i = 0; i < query.length; i++) {
            String[] conditions = new String[5];
            conditions[0] = query[i].split(" and ")[0];
            conditions[1] = query[i].split(" and ")[1];
            conditions[2] = query[i].split(" and ")[2];
            conditions[3] = query[i].split(" and ")[3].split(" ")[0];
            conditions[4] = query[i].split(" and ")[3].split(" ")[1];
            int count = 0;
            for (int j = 0; j <list.size(); j++){
                if (isPass(list.get(j), conditions)) count++;
            }
            answer[i] = count;
        }
        return answer;
    }
    private boolean isPass(String[] developer, String[] conditions){
        if (!conditions[0].equals("-") && !developer[0].equals(conditions[0])) return false;
        if (!conditions[1].equals("-") && !developer[1].equals(conditions[1])) return false;
        if (!conditions[2].equals("-") && !developer[2].equals(conditions[2])) return false;
        if (!conditions[3].equals("-") && !developer[3].equals(conditions[3])) return false;
        if (!conditions[4].equals("-") && Integer.parseInt(developer[4]) < Integer.parseInt(conditions[4])) return false;
        return true;
    }
}
