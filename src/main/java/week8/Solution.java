/*
    https://programmers.co.kr/learn/courses/30/lessons/70130
    [프로그래머스][월간 코드 챌린지 시즌1] 스타수열
 */
package week8;

import java.util.HashSet;
import java.util.LinkedList;

class Solution {
    public int solution(int[] a) {
        int answer =0;
        LinkedList<Integer> save = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> retain = new HashSet<>();
//        LinkedList<Integer> ans = new LinkedList<>();
        for(int i=0; i<a.length; i++){
            if(save.size()==0) save.add(a[i]);
            else if(save.size()==1 && save.get(0)!=a[i]) {
                int prev = save.remove();
                if(retain.size()==0){
                    retain.add(prev);
                    retain.add(a[i]);
//                    ans.add(prev);
//                    ans.add(a[i]);
                    answer+=2;
                    continue;
                }
                set.add(prev);
                set.add(a[i]);
//                System.out.println(retain+" "+set);
                retain.retainAll(set);
                if(retain.size()==0){
//                    System.out.println("교집합 없음 ");
                    save.add(prev);
                    continue;
                }
//                System.out.println("교집합 > "+retain+" "+set);
                set.removeAll(set);
                answer +=2;
//                System.out.println("지우ㄱㅣ > "+retain+" "+set+" ans "+ans);
            }

        }
        return answer;
    }
}