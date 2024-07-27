package com.adjh.springboottest.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * JUnit5에서 제공하는 메서드를 각각 테스트 해보는 예시
 *
 * @author : jonghoon
 * @fileName : MethodFormatTest
 * @since : 7/27/24
 */
public class MethodFormatTest {

    @Test
    @DisplayName("assertEquals 활용예시")
    public void assertEqualsTest() {
        String expect = "Something";
        String actual = "Something";
        Assertions.assertEquals(expect, actual);
    }

    @Test
    @DisplayName("assertNotEquals 활용예시")
    public void assertNotEqualsTest() {
        String expect = "Something";
        String actual = "Hello";
        Assertions.assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("assertTrue 활용예시")
    public void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;
        Assertions.assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("assertFalse 활용예시")
    public void assertFalseTest() {
        Integer a = 10;
        Integer b = 8;
        Assertions.assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("assertionThrows 활용예시")
    public void assertionThrowsTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러 ");
        });
    }

    @Test
    @DisplayName("assertionNotNull 활용예시")
    public void assertionNotNullTest() {
        String value = "Hello";
        Assertions.assertNotNull(value);
    }

    @Test
    @DisplayName("IterableEquals 활용예시")
    public void IterableEqualsTest() {
        List<Integer> expect = List.of(1, 2);
        List<Integer> actual = List.of(1, 2);
        Assertions.assertIterableEquals(expect, actual);
    }

    @Test
    @DisplayName("assertAll 활용예시")
    public void assertionAllTest() {
        String expect = "Something";
        String actual = "Something";
        Assertions.assertEquals(expect, actual);
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);
        Assertions.assertIterableEquals(list1, list2);

        Assertions.assertAll("Assert All", List.of(
                () -> Assertions.assertEquals(expect, actual),
                () -> Assertions.assertIterableEquals(list1, list2)
        ));
    }
}
