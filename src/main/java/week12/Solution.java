/*
    https://programmers.co.kr/learn/courses/30/lessons/42884#
    [프로그래머스][탐욕법(Greedy)] 단속 카메라
 */
package week12;

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        List<Car> list = new ArrayList<>();
        for (int[] route : routes) {
            list.add(new Car(route[0], route[1]));
        }
        Collections.sort(list);
        Car before = list.get(0);
        for (int i = 1; i<list.size(); i++){
            Car next = list.get(i);
            if (before.endTime >= next.endTime) {
                before = next;
            } else if (before.endTime < next.startTime){
                answer++;
                before = next;
            }
        }

        return answer;
    }
    static class Car implements Comparable<Car>{
        int startTime;
        int endTime;
        Car (int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Car o) {
            return this.startTime - o.startTime;
        }
    }
}
