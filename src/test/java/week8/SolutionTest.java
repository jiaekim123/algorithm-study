package week8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals(0, new Solution().solution(new int[]{0}));
        assertEquals(4, new Solution().solution(new int[]{5, 2, 3, 3, 5, 3}));
        assertEquals(8, new Solution().solution(new int[]{0, 3, 3, 0, 7, 2, 0, 2, 2, 0}));
    }
}