/*
    https://programmers.co.kr/learn/courses/30/lessons/60059
    [프로그래머스][2020 KAKAO BLIND RECRUITMENT] 자물쇠와 열쇠
 */
package week10;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        // n,m 제한이 크지 않으니까 다해본다
        lock = copyLock(lock, key.length);

        if (matching(key, lock)) return true;
        for (int i = 0; i < 3; i++) {

            key = left90(key);
            if (matching(key, lock)) {
                return true;
            }
        }
        return false;
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

    public int[][] copyLock(int[][] lock, int m) {
        int len = lock.length;
        int[][] newLock = new int[len + (2 * m)][len + (2 * m)];
        for (int i = m; i < len + (2 * m) - m; i++) {
            for (int j = m; j < len + (2 * m) - m; j++) {
                newLock[i][j] = lock[i - m][j - m];
            }
        }
        return newLock;
    }

    public boolean matching(int[][] key, int[][] lock) {
        int len = key.length;
        int max = lock.length;
        for (int i = 1; i < max - len; i++) {
            for (int j = 1; j < max - len; j++) {
                int[][] result = moveKey(key, lock, i, j);
                if (checkLock(result, len)) return true;
            }
        }
        return false;
    }

    public int[][] moveKey(int[][] key, int[][] lock, int lx, int ly) {
        int n = key.length;
        int[][] ret = new int[lock.length][lock.length];

        for (int i = n; i < ret.length - n; i++) {
            for (int j = n; j < ret.length - n; j++) {
                ret[i][j] = lock[i][j];
            }
        }

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                ret[lx + i][ly + j] = key[i][j] + lock[lx + i][ly + j];
            }
        }

        return ret;
    }

    public boolean checkLock(int[][] lock, int start) {
        for (int i = start; i < lock.length - start; i++) {
            for (int j = start; j < lock.length - start; j++) {
                if (lock[i][j] != 1) return false;
            }
        }
        return true;
    }
}