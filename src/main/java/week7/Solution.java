/*
    https://programmers.co.kr/learn/courses/30/lessons/42898
    [프로그래머스][동적계획법] 등굣길
 */
package week7;

class Solution {

    public int solution(int m, int n, int[][] puddles) {
        int [][]d = new int [n][m];
        for(int []p : puddles){
            d[p[1]-1][p[0]-1]=-1;
        }
        d[0][0]=1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(d[i][j] == -1) {
                    d[i][j] = 0;
                    continue;
                }
                if(i!=0) d[i][j]+=d[i-1][j]%1000000007;
                if(j!=0) d[i][j]+=d[i][j-1]%1000000007;
//                else if(i==0 || j==0) d[i][j] = 1;
//                else {
//                    d[i][j] = d[i-1][j] + d[i][j-1];
//                }
            }
        }

        return  d[n-1][m-1]%1000000007;
    }
}