/*
    https://programmers.co.kr/learn/courses/30/lessons/60058
    [프로그래머스][2020 KAKAO BLIND RECRUITMENT] 괄호 변환
 */
package week13;

import java.util.Stack;

class Solution {
    public String solution(String p) {
        String answer = solve(p);
        return answer;
    }

    public String solve(String p) {
        if (p.equals("")) return p; // 1 단계

        // 2 단계
        int index = find(p);
        String u = p.substring(0, index);
        String v = p.substring(index, p.length());
        boolean check = isRight(u);
        if (check) return u + solve(v); // 3-1
        else { // 4 단계
            String ret = "(" + solve(v) + ")";
            u = u.substring(1, u.length() - 1);
            String new_u = "";
            for (int i = 0; i < u.length(); i++) {
                if (u.charAt(i) == ')') new_u += "(";
                else new_u += ")";
            }
            return ret + new_u;
        }
    }

    public int find(String s) {
        int right = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) return i + 1;
        }
        return -1;
    }

    public boolean isRight(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') cnt++;
            else cnt--;
            if (cnt < 0) return false;
        }
        return true;
    }
}