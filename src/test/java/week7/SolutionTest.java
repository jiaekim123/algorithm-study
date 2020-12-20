package week7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals(4, new Solution().solution(4, 3, new int[][]{{2, 2}}));
    }
}