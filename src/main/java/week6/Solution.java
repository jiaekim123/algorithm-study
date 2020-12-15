/*
    https://programmers.co.kr/learn/courses/30/lessons/17680
    [프로그래머스][2018 KAKAO BLIND RECRUITMENT] 1차 캐시
 */
package week6;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<City> queue = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {

            if (queue.contains(new City(cities[i]))) {
                answer++;
            } else {
                if (queue.size() >= cacheSize) {
                    queue.poll();
                }
                queue.offer(new City(cities[i]));
                answer += 5;
            }
        }
        return answer;
    }
}

class City {
    String name;

    public City(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.toUpperCase().equals(city.name.toUpperCase());
    }
}