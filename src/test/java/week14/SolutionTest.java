package week14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals(14, new Solution().solution(new int[][] {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0));
        assertEquals(16, new Solution().solution(new int[][] {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 0, 1));
    }
}