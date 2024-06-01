package com.adjh.springboottest.unit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 중첩화 된 테스트
 *
 * @author : lee
 * @fileName : NestedTests
 * @since : 12/8/23
 */
class NestedTests {

    @Nested
    class CalcTests {
        @Test
        @DisplayName("두 숫자의 합 테스트")
        void testSum() {
            int a = 5;
            int b = 7;
            int expectedSum = 12;
            int actualSum = a + b;
            Assertions.assertEquals(expectedSum, actualSum);
        }

        @DisplayName("두 숫자의 합 테스트")
        @Test
        void testAddingTwoNumbers() {
            // given : 초기 상태 지정
            int a = 2;
            int b = 3;

            // when : 테스트 동작 정의
            int result = Calculator.add(a, b);

            // then : 검증
            int expected = 5;
            Assertions.assertEquals(expected, result);
            System.out.println("Test for adding two numbers passed");
        }

        @Test
        @DisplayName("두 수의 값 나누기 ")
        void testDividingTwoNumbers() {
            // given : 초기 상태 지정
            int a = 10;
            int b = 2;

            // when : 테스트 동작 정의
            int result = Calculator.divide(a, b);

            // then : 검증
            int expected = 5;
            Assertions.assertEquals(expected, result);
            System.out.println("Test for dividing two numbers passed");
        }
    }

    @Nested
    class IsNotNullTest {
        @Test
        @DisplayName("Object NULL 여부 테스트")
        void testIsNotNull() {
            Object obj = null;
            Assertions.assertNull(obj);
            System.out.println("[+] @test assertNull Annotation");
        }

    }

}
