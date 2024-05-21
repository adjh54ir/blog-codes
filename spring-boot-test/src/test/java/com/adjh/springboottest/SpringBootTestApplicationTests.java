package com.adjh.springboottest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("두 숫자의 합 테스트")
    void testSum() {
        int a = 5;
        int b = 7;
        int expectedSum = 12;
        int actualSum = a + b;
        Assertions.assertEquals(expectedSum, actualSum);
    }

}
