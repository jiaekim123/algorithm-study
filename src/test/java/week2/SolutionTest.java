package week2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals(4, new Solution().solution(5, 12));
        assertEquals(3, new Solution().solution(2, 11));
        assertEquals(-1, new Solution().solution(5, 31168));
    }
}