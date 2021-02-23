/*
    https://programmers.co.kr/learn/courses/30/lessons/60058
    [프로그래머스][2020 KAKAO BLIND RECRUITMENT] 괄호 변환
 */
package week13;

import java.util.*;

class Solution {
    public String solution(String p) {
        return makeCorrect(p);
    }
    // 균형잡힌 문자열인 index 가져오기 -> '('와 ')'의 갯수가 같을 경우.
    private int getBalancedUIndex(String s){
        // p를 두 균형잡힌 괄호 문자열 u, v로 쪼갠다. (u는 더이상 쪼갤 수 없는 균형잡힌 문자열이다.)
        // (1) int first, int last의 수가 0보다 크고 동일할 때 u라고 한다.
        int first = 0; // '('
        int last = 0; // ')'
        int uIndex = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if (chars[i] == '(') first++;
            else last++;
            if (first > 0 && last > 0 && first == last) return i;
        }
        return uIndex;
    }
    // 올바른 괄호 문자열인지 확인하는 함수
    // Stack '('가 나오면 넣고, ')'가 나오면 뺀다. 만약 뺄 것이 없는데 ')'가 나오면 return false.
    private boolean isCorrect(String s){
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars){
            if (c == '(') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                if (stack.peek() == '(') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty() ? true : false;
    }
    // 두 균형잡힌 문자열로 쪼개고 올바른 괄호로 만드는 메서드 (재귀호출)
    private String makeCorrect(String s){
        // 빈 문자열이면 그대로 반환
        if (s.length() == 0) return s;
        // u가 올바른 괄호 문자열인지 확인
        int uIndex = getBalancedUIndex(s);
        String u = s.substring(0, uIndex + 1);
        String v = "";
        if (uIndex != s.length() - 1) v = s.substring(uIndex + 1);
        if (isCorrect(u)){
            if (v.length()!=0) return u + makeCorrect(v);
            else return u;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(makeCorrect(v));
            sb.append(')');
            sb.append(makeNewU(u));
            return sb.toString();
        }
    }
    //  u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
    private String makeNewU(String s){
        String u = s.substring(1, s.length()-1);
        char[] chars = u.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }
}