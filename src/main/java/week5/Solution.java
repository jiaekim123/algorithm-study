/*
    https://programmers.co.kr/learn/courses/30/lessons/42627
    [프로그래머스][힙] 디스크 컨트롤러
 */
package week5;

import java.util.*;

class Solution {

    class Disk implements Comparable<Disk>{
        int start, time;
        Disk(int start, int time){
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(Disk o) {
            return this.time - o.time;
        }
    }

    public int solution(int[][] jobs) {
        LinkedList<Disk> list = new LinkedList<>(); // 작업 리스트
        PriorityQueue<Disk> pq = new PriorityQueue<>(); // 대기열

        // jobs 들어온 순서 대로 정렬
        Arrays.sort(jobs, (j1, j2) -> j1[0] == j2[0] ? j1[1] - j2[1] : j1[0]-j2[0]);

        // 작업 리스트
        for(int []j: jobs){
            list.add(new Disk(j[0], j[1]));
        }

        // 작업시간이 제일 작은것부터 정렬 - 우선순위 큐
        int sum = 0;
        int lastTime = 0;
        while(!pq.isEmpty() || !list.isEmpty()){
            boolean isNew = false; // 새 작업인가

            int len = list.size();
            // 다음 대기 할 것들을 찾는다
            Iterator<Disk> iter = list.iterator();
            while (iter.hasNext()){
                Disk d = iter.next();
                if(d.start > lastTime) break;
                pq.add(d);
                iter.remove();
            }

//            for(int i = 0; i<len; i++){
//                Disk d = list.get(i);
//                // 요청 시간이 이전에 끝난 시간 보다 더 크면 대기하고 있지 않다.
//                if(d.start > lastTime) break;
//                pq.add(d); // 대기열에 추가
//                list.remove(i); //봐야할 작업리스트에서 삭제
//            }
            // 대기하고 있는 것이 없으면(=start 시간에 작업할 수 있을 때) , 작업 순서대로 수행
            if(pq.size() == 0){
                pq.add(list.get(0));
                list.remove(0);
                isNew = true;
            }

            // 대기열에서 꺼낸다
            Disk now = pq.poll();
            if(isNew){ // 작업순으로 수행했을 때 (대기시간이 0)
                sum += now.time;
                lastTime = now.start + now.time;
            }else { // 대기시간이 있을 때
                sum += lastTime-now.start + now.time;
                lastTime += now.time;
            }
        }
        return sum/jobs.length;
    }
}