/*
    https://programmers.co.kr/learn/courses/30/lessons/60058
    [프로그래머스][2020 KAKAO BLIND RECRUITMENT] 괄호 변환
 */
package week13;

import java.util.Stack;

class Solution {
    public String solution(String p) {
        String answer = solve(p);
        System.out.println(answer);
        return answer;
    }

    public String solve(String p) {
        if (p.equals("")) return p; // 1 단계
        int index = 1;
        while (true) {
            // 2단계
            String u = p.substring(0, index);
            String v = p.substring(index, p.length());
            int f = find(u);
            if (f == 0) return u + solve(v); // 3-1
            else if (f == 2) {
                index++;
                continue;
            } else { // 4 단계
                String ret = "(";
                ret += solve(v);
                ret += ")";
                u = u.substring(1, ret.length() - 1);
                String new_u = "";
                for (int i = 0; i < u.length(); i++) {
                    if (u.charAt(i) == ')') new_u += "(";
                    else new_u += ")";
                }
                return ret + new_u;
            }
        }
    }

    public int find(String s) {
        char[] c = s.toCharArray();
        int left = 0;
        int right = 0;
        Stack<Character> st = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (c[i] == '(') {
                left++;
                st.push(c[i]);
            } else {
                right++;
                if (st.size() != 0 && st.peek() == '(') st.pop();
                else st.push(c[i]);
            }

        }
        if (st.size() == 0) return 0; // 올바른 문자열
        if (left == right) return 1; // 균형잡힌 문자열
        return 2; // 예외
    }

}