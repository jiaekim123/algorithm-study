/*
    https://programmers.co.kr/learn/courses/30/lessons/72412
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 순위 검색
 */
package week11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Solution {
    public int[] solution(String[] info, String[] query) {

        int[] answer = new int[query.length];
        for (int t = 0; t < query.length; t++) {
            String[] q = query[t].split("and");

            String lang = q[0].contains("-") ? "" : q[0].replace(" ", "");
            String position = q[1].contains("-") ? "" : q[1].replace(" ", "");
            String career = q[2].contains("-") ? "" : q[2].replace(" ", "");

            String[] q2 = q[3].split(" ");

            String food = q2[1].contains("-") ? "" : q2[1].replace(" ", "");
            int score = Integer.parseInt(q2[2]);
            System.out.println("Query " + lang + " " + position + " " + career + " " + food + " " + score);


            Stream<String> ans = Arrays.stream(info).filter((s) -> {
                int jumsu = Integer.parseInt(s.split(" ")[4]);
                if (s.contains(lang) && s.contains(position) && s.contains(career) && s.contains(food) && jumsu >= score) {
                    System.out.println(s);
                    return true;
                }
                return false;
            });
            answer[t] = ans.toArray().length;
            System.out.println(answer[t]);
        }
        return answer;
    }
}