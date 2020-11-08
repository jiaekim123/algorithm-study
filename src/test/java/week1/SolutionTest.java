package week1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals(2, new Solution().solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        assertEquals(2, new Solution().solution(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
    }
}