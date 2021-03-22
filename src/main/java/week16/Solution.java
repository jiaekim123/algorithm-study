/*
    https://programmers.co.kr/learn/courses/30/lessons/72414
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 광고 삽입
 */
package week16;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // time -> int (초단위)
    public int getValue(String s) {
        String[] arr = s.split(":");
        return Integer.parseInt(arr[0]) * 60 * 60 + Integer.parseInt(arr[1]) * 60
                + Integer.parseInt(arr[2]);
    }

    // int -> string
    public String getString(int n) {
        int s = n % 60;
        n /= 60;
        int m = n % 60;
        n /= 60;
        int h = n;
        String ret = "";
        if (h < 10) ret += "0";
        ret += String.valueOf(h) + ":";
        if (m < 10) ret += "0";
        ret += String.valueOf(m) + ":";
        if (s < 10) ret += "0";
        ret += String.valueOf(s);
        return ret;
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        int[] sumArray = new int[360001]; // 100 시간

        int totalTime = getValue(play_time);
        int advTime = getValue(adv_time);

        // in and out 구하기
        for (String s : logs) {
            int start = getValue(s.split("-")[0]);
            int end = getValue(s.split("-")[1]);
            for (int i = start; i < end; i++) sumArray[i]++; // 시청자 수 누적
        }

        int idx = 0;
        long sum = 0;
        long maxSum = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < advTime; i++) {
            sum += sumArray[i];
            q.add(sumArray[i]);
        }
        maxSum = sum;

        for (int i = advTime; i < totalTime; i++) {
            sum += sumArray[i];
            q.add(sumArray[i]);
            sum -= q.poll();
            if(sum > maxSum){
                idx = i-advTime+1;
                maxSum = sum;
            }
        }
        return getString(idx);
    }

}