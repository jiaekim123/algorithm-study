/*
    https://programmers.co.kr/learn/courses/30/lessons/17686
    [프로그래머스][2018 KAKAO BLINE RECRUITMENT] 파일명 정렬 문제
 */
package week4;

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static class File implements Comparable<File>{
        private String head;
        private String num;
        private String tail;
        private String s;

        File(String head, String num, String tail, String s){
            this.head = head;
            this.num = num;
            this.tail = tail;
            this.s = s;
        }

        @Override
        public int compareTo(File o) {
            int h = this.head.compareTo(o.head);
            if(h>0) return 1;
            else if(h<0) return -1;
            else {
                int num = Integer.parseInt(this.num);
                int n = Integer.parseInt(o.num);
                if(num > n) return 1;
                else if( num < n) return  -1;
            }
            return 0;
        }
    }

    public String[] solution(String[] files) {
        ArrayList<File> list = new ArrayList<>();
        for(String f: files){
            char []arr = f.toCharArray();
            String head = "";
            String num = "";
            String tail = "";
            int n = -1;
            int t = -1;

            for(int i=0; i<arr.length; i++){
                char c = arr[i];
                if(c>=48 && c<=57 && n == -1) n = i;
                else if(n!=-1 && (c<48 || c>57 || c==' ') && t==-1) t=i;
                else if(n==-1 && (c<48 || c>57 )){
                    if(c>=65 && c<=90) c+=32;
                    head+=String.valueOf(c);
                }
            }

            System.out.println(t);
            if(t!=-1) {
                num = f.substring(n, t);
                tail = f.substring(t, arr.length);
            }
            else if(n!=-1)num = f.substring(n,arr.length);
//            System.out.println(head+" "+num+" "+tail);
            list.add(new File(head, num, tail, f));
        }
        Collections.sort(list);
        String []answer = new String[list.size()];

        for(int i =0; i<list.size(); i++){
            answer[i] = list.get(i).s;
//            System.out.println(answer[i]);
        }

        return answer;
    }

}