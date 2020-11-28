package week4;

import org.junit.jupiter.api.Test;
import week4.Solution;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void solution() {
        assertArrayEquals(new String[]{"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"},
                new Solution().solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"}));
        assertArrayEquals(new String[]{"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"},
                new Solution().solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"}));
    }
}