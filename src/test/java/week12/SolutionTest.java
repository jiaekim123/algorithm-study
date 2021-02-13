package week12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals(2, new Solution().solution(new int[][] {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }
}