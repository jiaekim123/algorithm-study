/*
    https://programmers.co.kr/learn/courses/30/lessons/42627
    [프로그래머스][힙] 디스크 컨트롤러
 */
package week5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[][] jobs) {

        // jobs를 요청시간 순서대로 정렬
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        int i = 0; // queue 에 넣은 총 갯수
        int count = 0; // queue 에서 꺼내온 총 갯수
        int time = 0; // 현재 시간
        int totalTime = 0; // 총 걸린 시간
        Queue<Job> queue = new PriorityQueue<>();

        while (count < jobs.length){
            // 현재 시간보다 requestTime이 작은 애가 있으면 큐에 넣는다.
            while (i < jobs.length && jobs[i][0] <= time) {
                queue.offer(new Job(jobs[i][0], jobs[i][1]));
                i++;
            }

            if (!queue.isEmpty()) {
                // queue에서 Job을 수행하고 time을 올리고 totalTime에 반영한다.
                Job job = queue.poll();
                time += job.workingTime;
                totalTime += (time - job.requestTime);
                count++;
            } else {
                time = jobs[i][0];
            }
        }
        return (int)(totalTime / jobs.length);
    }
}

class Job implements Comparable<Job>{
    int requestTime;
    int workingTime;

    public Job(int requestTime, int workingTime){
        this.requestTime = requestTime;
        this.workingTime = workingTime;
    }

    // workingTime이 작은 순서대로 순서로 정렬
    @Override
    public int compareTo(Job job) {
        return this.workingTime - job.workingTime;
    }
}