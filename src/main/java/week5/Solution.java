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
        int totalTime = 0;
        // jobs를 request순으로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        int i = 0;
        int time = 0;
        Queue<Job> queue = new PriorityQueue<>();
        queue.offer(new Job(jobs[i][0], jobs[i][1]));
        i++;
        while (!queue.isEmpty()) {
            // queue에서 Job을 수행하고 time을 올리고 totalTime에 반영한다.
            Job job = queue.poll();
            time += job.workingTime;
            totalTime += (time - job.requestTime);

            // 현재 시간보다 requestTime이 작은 애가 있으면 큐에 넣는다.
            while (i < jobs.length && jobs[i][0] <= time) {
                queue.offer(new Job(jobs[i][0], jobs[i][1]));
                i++;
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