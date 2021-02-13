/*
    https://programmers.co.kr/learn/courses/30/lessons/60059
    [프로그래머스][2020 KAKAO BLIND RECRUITMENT] 자물쇠와 열쇠
 */
package week10;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int keyLen = key.length;
        int lockLen = lock.length;
        int[][] expansionLock = getExpansionLock(lock, keyLen, lockLen);
        int[][] rotateKey = rotateRight90(key);
        for (int r = 0; r < 4; r++) { // 회전
            for (int x = 0; x < expansionLock.length - (keyLen - 1); x++) { // x를 이동하면서 비교
                for (int y = 0; y < expansionLock.length - (keyLen - 1); y++) { // y를 이동하면서 비교
                    expansionLock = getExpansionLock(lock, keyLen, lockLen); // expansionLock 초기화
                    for (int i = 0; i < keyLen; i++) { // key와 lock 합치기
                        for (int j = 0; j < keyLen; j++) {
                            expansionLock[x + i][y + j] += rotateKey[i][j];
                        }
                    }
                    if (isOpenLock(expansionLock, keyLen, lockLen)) return true;
                }
            }
            rotateKey = rotateRight90(rotateKey);
        }
        return false;
    }

    public boolean isOpenLock(int[][] expansionLock, int keyLen, int lockLen) {
        for (int i = keyLen - 1; i < lockLen + (keyLen - 1); i++) {
            for (int j = keyLen - 1; j < lockLen + (keyLen - 1); j++) {
                if (expansionLock[i][j] != 1) return false;
            }
        }
        return true;
    }

    public int[][] getExpansionLock(int[][] lock, int keyLen, int lockLen) {
        int len = lockLen + 2 * (keyLen - 1);
        int[][] result = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                result[i][j] = -1; // 확장 배열 초기화
            }
        }
        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                result[i + (keyLen - 1)][j + (keyLen - 1)] = lock[i][j]; // 확장 배열 가운데에 lock 위치
            }
        }
        return result;
    }

    public int[][] rotateRight90(int[][] key) { // key 오른쪽 90도 회전
        int len = key.length;
        int[][] result = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                result[j][len - i - 1] = key[i][j];
            }
        }
        return result;
    }

}
