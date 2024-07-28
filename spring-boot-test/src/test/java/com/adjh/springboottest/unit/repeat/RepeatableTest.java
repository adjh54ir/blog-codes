package com.adjh.springboottest.unit.repeat;

import com.adjh.springboottest.modules.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * JUnit 반복 테스트
 *
 * @author : jonghoon
 * @fileName : RepeatableTest
 * @since : 7/28/24
 */
public class RepeatableTest {

    @DisplayName("덧셈 테스트")
    @RepeatedTest(value = 5)
    public void repeatedAddTest(TestInfo testInfo) {
        // Arrange: 준비
        Calculator myCalculator = new Calculator();

        // Act: 행동
        myCalculator.add(10.0);

        // Assert: 단언 검증
        Assertions.assertEquals(10.0, myCalculator.getResult());

        // 현재 반복 횟수와 전체 반복 횟수를 출력
        System.out.println(testInfo.getDisplayName());
    }

    @DisplayName("덧셈 테스트")
    @RepeatedTest(value = 5, name = "{displayName} :: 반복 {currentRepetition} / {totalRepetitions}")
    public void repeatedAddTest2(TestInfo testInfo) {

        System.out.println("displayName  : " + testInfo.getDisplayName());
        System.out.println("testClass : " + testInfo.getTestClass());
        System.out.println("testMethod : " + testInfo.getTestMethod());
        System.out.println("Tags : " + testInfo.getTags());

        // Arrange: 준비
        Calculator myCalculator = new Calculator();

        // Act: 행동
        myCalculator.add(10.0);

        // Assert: 단언 검증
        Assertions.assertEquals(10.0, myCalculator.getResult());

        // 현재 반복 횟수와 전체 반복 횟수를 출력
        System.out.println(testInfo.getDisplayName());
    }



    /**
     * parameterized 기능을 이용한 테스트
     *
     * @param addValue
     * @param expectedValue
     */
    @DisplayName("각기 다른 파라미터 값에 따라 반복하여 테스트 ")
    @ParameterizedTest
    @MethodSource("parameterizedTestParameters")
    public void parameterized(Double addValue, Double expectedValue) {

        // Arrange : 준비
        Calculator myCalculator = new Calculator();

        // Act : 행동
        myCalculator.add(addValue);

        // Assert : 단언 검증
        Assertions.assertEquals(expectedValue, myCalculator.getResult());
    }
    /**
     * 전달하려는 파라미터를 구성합니다.
     *
     * @return
     */
    public static Stream<Arguments> parameterizedTestParameters() {
        return Stream.of(
                Arguments.of(10.0, 10.0),
                Arguments.of(8.0, 8.0),
                Arguments.of(4.0, 4.0),
                Arguments.of(16.0, 16.0)
        );
    }

}
