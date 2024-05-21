package com.adjh.javaalgorithm.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 탐색 알고리즘 > 그리디 알고리즘(탐욕법)
 *
 * @author : jonghoon
 * @link : https://adjh54.tistory.com/212
 * @fileName : SearchGreedyController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/search/greedy")
public class SearchGreedyController {


    /**
     * [프로그래머스] level3 - 거스름돈 계산
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/12907
     * @since 2023.06.24
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {

        Integer[] coins = {100, 10, 500, 50};   // 동전 종류
        int money = 1260;                       // 거스름돈
        int count = 0;                          // 동전 사용 개수

        Map<String, Object> resultMap = new HashMap<>();

        // 1. 선택 절차 적용 : 거스름돈 문제에서 가장 가치가 큰 동전부터 선택을 합니다.
        Arrays.sort(coins, Comparator.reverseOrder());

        // 2. 적절성 검사 : 만약 선택된 동전의 가치가 거스름돈보다 크다면 다음으로 작은 동전을 선택한다.
        for (int i = 0; i < coins.length; i++) {
            count += money / coins[i];
            money %= coins[i];
            resultMap.put(String.valueOf(coins[i]), count);
        }
        // 3. 해답 검사 : 합이 일치하면 거스름돈 문제가 해결되었습니다.
        if (money == 0) {
            System.out.println("거스름돈 문제가 해결되었습니다.");
        }

        // 결과값 : { "500" : 2, "100" : 4, "50" : 5, "10": 6 }

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * [프로그래머스] level1 - 체육복
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42862
     * @since 2023.06.24
     */
    @PostMapping("/2")
    public ResponseEntity<Object> question2(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length; // 체육복이 없는 학생 수

        // 1. 선택 절차
        // 학생 번호를 기준으로 정렬합니다.
        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 2. 적절성 검사
        // 체육복을 잃어버린 학생 중 여벌이 있는 학생에게 빌려줄 수 있는 경우
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) { // 여벌 체육복을 가진 학생이 체육복을 잃어버린 경우
                    answer++; // 체육복을 빌려받은 학생 수 증가
                    reserve[j] = -1; // 빌려준 학생은 더 이상 빌려줄 수 없도록 표시
                    break;
                }
            }
        }
        // 3. 해답 검사
        // 체육복을 잃어버린 학생 중 여벌이 없는 학생에게 빌려줄 수 있는 경우
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (reserve[j] == lost[i] - 1 || reserve[j] == lost[i] + 1) {
                    answer++;
                    reserve[j] = -1;
                    break;
                }
            }
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
