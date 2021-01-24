/*
    https://programmers.co.kr/learn/courses/30/lessons/60059
    [프로그래머스][2020 KAKAO BLIND RECRUITMENT] 자물쇠와 열쇠
 */
package week10;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        // n,m 제한이 크지 않으니까 다해본다
        boolean answer = false;
        matching(key, lock);
        for (int i = 0; i < 3; i++) {
            key = left90(key);
            if(matching(key, lock)) return true;
//            System.out.println();
        }
        return answer;
    }

    public void print(int[][] k) {
        int l = k.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                System.out.print(k[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] left90(int[][] key) {
        int len = key.length;
        int[][] ret = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                ret[j][len - i - 1] = key[i][j];
            }
        }
        return ret;
    }

    public boolean matching(int[][] key, int[][] lock) {
        int len = key.length;
        int max = lock.length;
        boolean check = true;
        for (int c = 0; c < max-1; c++) { // 0, 1, 2
            for (int l = 0; l < max-1; l++) { // 0, 1, 2
                check = true;
//                System.out.println(c+" "+l+"일때 ");
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < len; j++) {
                        if (i + c >= max || j + l >= max) continue;
                        if (key[i][j] == lock[i + c][j + l]) {
//                            System.out.println(i + "." + j + " :: " + (i+c) + "." + (j+l));
                            check = false;
                        }
                        if(!check) break;
                    }
                    if(!check) break;
                }
                if(check) {
//                    System.out.println(c+" "+l+"일때 check 는 true");
                    return true;
                }
            }
        }
        return false;
    }
}