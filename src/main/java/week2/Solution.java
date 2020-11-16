/*
    https://programmers.co.kr/learn/courses/30/lessons/42895
    [프로그래머스][동적계획법] N으로 표현
 */
package week2;

import java.util.HashSet;

class Solution {
    final static int MAX_COUNT = 8;
    public int solution(int N, int number) {
        HashSet<Integer>[] results = new HashSet[MAX_COUNT];

        for (int i = 0; i < MAX_COUNT; i++){
            if (dp(i, N, results).contains(number)) return i;
        }

        return -1;
    }
    private HashSet<Integer> dp(int i, int N, HashSet<Integer>[] results){
        HashSet<Integer> result = new HashSet<Integer>();
        int NN = 0;
        for (int j = 0; j < i; j++) NN = NN * 10 + N;
        result.add(NN);

        for (int j = 1; j < i; j++){
            HashSet<Integer> r1 = results[j];
            HashSet<Integer> r2 = results[i-j];
            for (int num1 : r1){
                for (int num2: r2){
                    result.add(num1 + num2);
                    result.add(num1 - num2);
                    result.add(num1 * num2);
                    if (num2 != 0) result.add(num1 / num2);
                }
            }
        }
        results[i] = result;
        return result;
    }
}