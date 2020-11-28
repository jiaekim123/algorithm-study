/*
  https://programmers.co.kr/learn/courses/30/lessons/49191
  [프로그래머스][그래프] 순위
 */
package week3;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
class Solution {
    static class Node implements Comparable<Node>{
        int name;
        LinkedList<Integer> child = new LinkedList<>();
        int value = 0;
        Node(int name){
            this.name = name;
        }
        @Override
        public int compareTo(Node o) {
            if(value == -1) return -1;
            if(this.value< o.value) return 1;
            else if(this.value> o.value) return -1;
            else return 0;
        }
    }
    public int solution(int n, int[][] results) {

        LinkedList<Node> nodes = new LinkedList<>();
        for(int i=0; i<n+1; i++){
            nodes.add(new Node(i));
            if(i==0)nodes.get(0).value = -1;
        }
        for(int i=0; i<results.length; i++){
            int win = results[i][0];
            int lose = results[i][1];
            // 자식들을 올린다
            nodes.get(win).child.add(lose);
            // 계층
            if(nodes.get(win).value >= nodes.get(lose).value){
                nodes.get(lose).value = nodes.get(win).value+1;
            }else if(Math.abs(nodes.get(win).value-nodes.get(win).value)!=1){
                nodes.get(win).value = nodes.get(lose).value-1;
            }
            LinkedList<Integer> downList = nodes.get(lose).child;
            for(int c : downList){
                if(nodes.get(lose).value >= nodes.get(c).value)
                    nodes.get(c).value= nodes.get(lose).value+1;
            }
//            System.out.println("win "+nodes.get(win).name+" "+nodes.get(win).value);
//            System.out.println("lose "+nodes.get(lose).name+" "+nodes.get(lose).value);
        }

        // value 기준으로 정렬
        Collections.sort(nodes);
        HashMap<Integer, Integer> map = new HashMap<>();
        int stopIndex = -1;
        for(int i=0; i<n; i++){
//            System.out.println("NAME : "+nodes.get(i).name+ " "+nodes.get(i).value+" ");
            if(map.containsKey(nodes.get(i).value)){
                int name = map.get(nodes.get(i).value);
                if(!nodes.get(name).child.contains( nodes.get(i).name )){
                    stopIndex = i;
                }
//                System.out.println(name+ " " + nodes.get(i).name);
                if(stopIndex!= -1) break;
            }else
                map.put(nodes.get(i).value, nodes.get(i).name); // key - value
        }

//        System.out.println(stopIndex+" ans wer "+ (n-stopIndex));



        return n-stopIndex;
    }
}