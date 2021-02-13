/*
    https://programmers.co.kr/learn/courses/30/lessons/42895
    [프로그래머스][동적계획법] N으로 표현
 */
package week2;

import java.util.HashSet;
import java.util.Set;

class Solution {
    /*
    * N을 n번 사용해서 얻는 수  */
    public int solution(int N, int number) {
        Set<Long>[] list = new HashSet[8];
        for(int i=0; i<8; i++)list[i] = new HashSet<>();
        String n = "";
        for(int i=0; i<8; i++){
            n+=String.valueOf(N);
            list[i].add(Long.parseLong(n)); // n을 i+1번 사용해서 만들 수 있는 수
        }
        // 1번 set + n-1번 set 사칙 연산해서 만들 수 있는 수
        for(int i=0; i<8; i++){ // i번 사용 할 때
            //list[i]에 추가 되는 수들
            for(int j=0; j<i; j++){ // 0+2, 1+1,
                // 사칙 연산 op1, op2;
                for(Long one: list[j]){ // list[0] set ~
                    for(Long two : list[i-j-1]){ // list[0] set ~
                        list[i].add(one+two);
                        list[i].add(one-two);
                        list[i].add(one*two);
                        if(two != 0) list[i].add(one/two);
                    }
                }
            }
        }
        for(int i=0; i<8; i++){
//            System.out.println(i+" :: "+list[i]);
            if(list[i].contains((long)number)){
                return i+1;
            }
        }
        return -1;
    }

}