package com.adjh.javaalgorithm.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 탐색 알고리즘 > 동적 계획법
 *
 * @author : jonghoon
 * @fileName : DynamicProgrammingController
 * @since : 5/25/24
 */
@RestController
@RequestMapping("/api/v1/search/dp")
public class DynamicProgrammingController {

    /**
     * [프로그래머스] default
     *
     * @return
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/1845
     */
    @PostMapping("/0")
    public ResponseEntity<Object> question0() {
        int answer = 0;
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [?] 숫자 카드
     *
     * @return
     * @link
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int answer = 0;
        int num = 8;
        int[] cards = {1, 4, 6};

        // num 개수 만큼 Integer의 최대값 배열로 구성합니다.
        int[] dp = new int[num + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        System.out.println("dp :: " + Arrays.toString(dp));

        // 첫번째 값은 0으로 세팅합니다.
        dp[0] = 0;

        // 두번째 값부터 배열을 순회합니다.
        for (int i = 1; i <= num; i++) {

            for (int card : cards) {

                // 현재 숫자(i)에서 카드를 뺀 값이 0 이상이고 그 인덱스에 해당하는 dp 배열의 최대 정수 값이 아닌 경우
                if (i - card >= 0 && dp[i - card] != Integer.MAX_VALUE) {

                    // dp[i]와 dp[i - card] + 1 중 작은 값을 dp[i]에 할당하는 것으로 이루어집니다.
                    dp[i] = Math.min(dp[i], dp[i - card] + 1);
                }
            }
        }

        //dp[num] 값이 최대 정수 값이면 -1을 반환하고, 그렇지 않으면 dp[num]을 반환합니다. 이것은 주어진 숫자를 만드는데 필요한 최소 카드 수를 나타냅니다.
        answer = dp[num] == Integer.MAX_VALUE ? -1 : dp[num];
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
