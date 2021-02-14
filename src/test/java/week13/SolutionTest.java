package week13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertEquals("(()())()", new Solution().solution("(()())()"));
        assertEquals("()", new Solution().solution(")("));
        assertEquals("()(())()", new Solution().solution("()))((()"));
    }
}