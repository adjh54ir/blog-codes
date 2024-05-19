package com.adjh.javaalgorithm.controller.exhaustive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 완전탐색 > 브루트 포스
 *
 * @author : jonghoon
 * @link : https://adjh54.tistory.com/227
 * @fileName : BruteForceController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/exhaustive/bruteForce")
public class BruteForceController {

    /**
     * [프로그래머스] level1 - 최소직사각형
     * 조건에 만족하는 모든 경우를 탐색하여 원하는 결과를 얻는 브루트 포스를 이용한 문제
     *
     * @return ResponseEntity
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/86491
     * @since 2023.07.23
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int answer = 0;
        int[][] size = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

        int width = 0;      // 총 계산된 카드의 너비
        int height = 0;     // 총 계싼된 카드의 높이

        // [STEP1] 2차원 배열을 1차원 배열로 순회합니다. (* 카드의 모든 경우를 순회합니다.)
        for (int[] card : size) {

            int cardItemWidth = card[0];    // n번 명함의 가로 길이
            int cardItemHeight = card[1];   // n번 명함의 세로 길이

            // [STEP2] 카드의 기로와 세로의 길이 중 최대 값과 이전 카드 사이즈를 비교하여 최종 너비값을 구합니다.
            width = Math.max(width, Math.max(cardItemWidth, cardItemHeight));

            // [STEP3] 카드의 가로와 세로의 길이 총 최소 값과 이전에 카드 사이즈를 비교하여 최종 높이값을 구합니다.
            height = Math.max(height, Math.min(cardItemWidth, cardItemHeight));
        }

        // [STEP4] 최종적으로 계산된 너비와 높이 값을 곱하여 결과값을 구합니다.
        answer = width * height;

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] level1 - 모의고사
     *
     * @return ResponseEntity
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42840?language=java
     * @since 2023.07.23
     */
    @PostMapping("/2")
    public ResponseEntity<Object> question14() {

//        int[] answers = new int[]{1, 3, 2, 4, 2};
        int[] answers = new int[]{1, 2, 3, 4, 5};
        List<Integer> personScoreList = Arrays.asList(0, 0, 0);

        // [STEP1] 수포자들의 문제를 찍는 패턴 정의
        int[] person1Pattern = new int[]{1, 2, 3, 4, 5};
        int[] person2Pattern = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3Pattern = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        // [STEP2] 문제 배열을 순회하면서 각각의 값을 Counting 합니다. (* 모든 경우의 수를 순회합니다)
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == person1Pattern[i % 5]) personScoreList.set(0, personScoreList.get(0) + 1);
            if (answers[i] == person2Pattern[i % 8]) personScoreList.set(1, personScoreList.get(1) + 1);
            if (answers[i] == person3Pattern[i % 10]) personScoreList.set(2, personScoreList.get(2) + 1);
        }
        // [STEP3] 구성한 리스트의 최대값을 구합니다.
        int max = Collections.max(personScoreList);

        // [STEP4] 최대값인 사용자를 구합니다.
        List maxPersonList = new ArrayList<>();
        for (int i = 0; i < personScoreList.size(); i++) {
            if (max == personScoreList.get(i)) maxPersonList.add(i + 1);
        }

        // [STEP5] 최종 배열로 구성합니다.(List to Array[])
        int[] answer = maxPersonList.stream().mapToInt(i -> (int) i).toArray();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    /**
     * [프로그래머스] leve2 - 카펫
     *
     * @return ResponseEntity
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42842
     * @since 2023.07.24
     */
    @PostMapping("/3")
    public ResponseEntity<Object> question16() {
        int[] answer = new int[2];
        int brown = 8;
        int yellow = 1;

        // [STEP1] 갈색 격자와 노란 격자를 합하여 전체의 개수를 구합니다.
        int sum = brown + yellow;

        // [STEP2] 그 합의 제곱근부터 1이 될 때까지 반복 수행합니다. (브루트 포스)
        for (int i = (int) Math.sqrt(sum); i >= 1; i--) {

            // [STEP3] 그 중 합의 약수인 경우를 찾습니다.
            if (sum % i == 0) {

                // [STEP4] 약수 중 합에서 가로의 길이를 나누어서 세로의 길이를 계산합니다.
                int j = sum / i;

                // [STEP5] 가로와 세로의 길이를 이용하여 노란색 타일의 개수가 맞는지 확인하고, 맞을 경우에는 해당 크기를 반환합니다.
                if ((i - 2) * (j - 2) == yellow) {
                    answer = new int[]{j, i};
                }
            }
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


}
