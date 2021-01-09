/*
    https://programmers.co.kr/learn/courses/30/lessons/70130
    [프로그래머스][월간 코드 챌린지 시즌1] 스타수열
 */
package week8;

class Solution {
    public int solution(int[] a) {
        int answer = -1;

        int []cnt = new int [a.length+1]; // a길이 미만의 수
        for(int i=0; i<a.length; i++){
            cnt[a[i]]++;
        } // 숫자 등장 횟수

        for(int i=0; i<cnt.length; i++){ // i를 공통 원소로 봤을 떄
            if(cnt[i] == 0) continue; // 교집합이 될 수 없는 것
            if(cnt[i]<= answer) continue; // 탐색 해보지 않아도 되는 경우
            int res = 0;
            for(int j=0; j<a.length-1; j++){ // 모든 원소에 대한 탐색
                if(a[j]!=i && a[j+1] !=i) continue; // 공통원소가 없으면 pass
                if(a[j]==a[j+1])continue; // 2개의 값이 달라야 함
                res++;
                j++;
            }
            answer = Math.max(answer, res); // 최대로 만들 수 있는 스타 수열 갯수 갱신
        }

        return answer*2; // 수열의 길이므로 *2
    }
}