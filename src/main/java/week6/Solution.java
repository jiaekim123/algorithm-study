/*
    https://programmers.co.kr/learn/courses/30/lessons/17680
    [프로그래머스][2018 KAKAO BLIND RECRUITMENT] 1차 캐시
 */
package week6;

import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        int idx = -1;
        if(cacheSize ==0) return 5*cities.length;
        for(String city : cities){
            city = city.toLowerCase(); // 소문자 변환
            idx = cache.indexOf(city);
            if(idx!=-1){
                answer+=1; // cache hit
                cache.remove(idx);
                cache.add(city);
            }
            else {
                answer+=5; // cache miss
                if(cacheSize<= cache.size())
                    cache.remove(0);
                cache.add(city);
            }
        }
        return answer;
    }
}