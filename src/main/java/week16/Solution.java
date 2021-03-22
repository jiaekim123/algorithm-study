/*
    https://programmers.co.kr/learn/courses/30/lessons/72414
    [프로그래머스][2021 KAKAO BLIND RECRUITMENT] 광고 삽입
 */
package week16;

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
        int m = (n / 60) % 60;
        int h = (n / 3600) % 60;
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
            ++sumArray[start];
            --sumArray[end];
        }
        for (int i = 1; i < totalTime; i++) {
            sumArray[i] += sumArray[i - 1]; // 구간 별 재생수
        }
        for (int i = 1; i < totalTime; i++) {
            sumArray[i] += sumArray[i - 1]; // 누적 재생수
        }
        Record record = new Record();
        record.sum = sumArray[advTime];

        for (int start = advTime + 1; start < totalTime; start++) {
            int sum = sumArray[start] - sumArray[start - advTime];
            if (sum > record.sum) {
                record.start = start - advTime + 1;
                record.sum = sum;
            }
        }
        return getString(record.start);
    }

    class Record {
        int sum = 0;
        int start = 0;
        int end = 0;
    }
}