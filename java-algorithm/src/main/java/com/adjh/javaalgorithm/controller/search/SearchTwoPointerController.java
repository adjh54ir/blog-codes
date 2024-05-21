package com.adjh.javaalgorithm.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 탐색 알고리즘 > 투포인터 알고리즘
 *
 * @author : lee
 * @link : https://adjh54.tistory.com/398
 * @fileName : SearchTwoPointerController
 * @since : 2024. 5. 21.
 */
@RestController
@RequestMapping("/api/v1/search/twoPointer")
public class SearchTwoPointerController {


    /**
     * [백준] 2018 - 수들의 합5
     *
     * @return
     * @link https://www.acmicpc.net/problem/2018
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int answer = 0;
        int input = 15;

        int startIdx = 1;
        int endIdx = 1;
        int sum = 1;
        int count = 1;

        // [STEP1] 1 ~ 15까지 수행한다.
        while (endIdx != input) {
            // [CASE1] 합이 input 값과 동일한 경우 : 조건에 만족하는 경우로 합계를 구하고 endIdx를 오른쪽으로 이동합니다.
            if (sum == input) {
                count++;
                endIdx++;
                sum += endIdx;
            }
            // [CASE2] 합이 input 보다 큰 경우 : startIndex 값을 빼고 startIndex의 값을 오른쪽으로 이동합니다.
            else if (sum > input) {
                sum -= startIdx;
                startIdx++;
            }
            // [CASE3] 합이 input 보다 작은 경우 : endIdx 값을 오른쪽으로 이동하고 합에 더합니다.
            else {
                endIdx++;
                sum += endIdx;
            }
        }
        answer = count;
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [백준] 1940 - 주몽
     *
     * @return
     * @link https://www.acmicpc.net/problem/1940
     */
    @PostMapping("/2")
    public ResponseEntity<Object> question2() {
        int answer = 0;

        int n = 6; // 재료의 개수
        int m = 9; // 갑옷을 만드는데 필요한 수
        String input3 = "2 7 4 1 5 3"; // 재료들

        // 1. 배열을 구성하며 문자열 배열을 정수 배열로 캐스팅 합니다.
        String[] materialStrArr = input3.split("");
        int[] materialArr = new int[materialStrArr.length];
        for (int i = 0; i < input3.split("").length; i++) {
            materialArr[i] = Integer.parseInt(input3.split("")[i]);
        }
        // 2. 정수 배열을 오름차순으로 정렬합니다.
        Arrays.sort(materialArr);

        int count = 0;          // 갑옷을 만들수 있는 가짓수
        int startIdx = 0;       // 투포인터의 시작 인덱스
        int lastIdx = n - 1;    // 투포인터의 마지막 인덱스

        // 3. 시작 인덱스 보다 마지막 인덱스 값이 크면 종료합니다.
        while (startIdx < lastIdx) {

            // 4.1. 시작 인덱스와 마지막 인덱스를 더했을때, m보다 작은 경우 : 시작 값 오른쪽 이동
            if (materialArr[startIdx] + materialArr[lastIdx] < m) {
                startIdx++;
            }
            // 4.2. 시작 인덱스와 마지막 인덱스를 더했을때, m보다 큰 경우 : 마지막 값을 왼쪽으로 이동
            else if (materialArr[startIdx] + materialArr[lastIdx] > m) {
                lastIdx--;
            }
            // 4.3. 시작 인덱스와 마지막 인덱스를 더했을때, m과 같은 경우 : 카운트 값을 올리고 시작 값을 오른쪽 이동, 끝값을 왼쪽으로 이동
            else {
                count++;
                startIdx++;
                lastIdx--;
            }

            // 5. 최종 카운팅 된 값을 반환합니다.
            answer = count;
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [백준] 1253 - 좋은수
     *
     * @return
     * @link https://www.acmicpc.net/problem/1253
     */
    @PostMapping("/3")
    public ResponseEntity<Object> question3() {
        int count = 0;
        int input1 = 10;

        // [STEP1] 문자열을 배열로 변환
        String input2 = "1 2 3 4 5 6 7 8 9 10";
        String[] inputArr = input2.split(" ");

        // [STEP2] 숫자 배열로 변형
        int[] inputIntArr = new int[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            inputIntArr[i] = Integer.parseInt(inputArr[i]);
        }
        // [STEP3] 숫자 배열을 순회하면서 값을 구함
        for (int i = 0; i < inputIntArr.length; i++) {
            int startIdx = 0;               // 시작 인덱스
            int endIdx = input1 - 1;        // 마지막 인덱스
            int findItem = inputIntArr[i];  // 순회되는 값

            // [STEP4] 값을 기준으로 다음 값과 비교합니다.
            while (startIdx < endIdx) {

                // [STEP4-1] 시작 값과 종료값의 합이 찾는 값과 같은 경우
                if (inputIntArr[startIdx] + inputIntArr[endIdx] == findItem) {

                    // [CASE1] 시작 인덱스, 종료 인덱스와 같이 않을 경우 : 종료
                    if (startIdx != i && endIdx != i) {
                        count++;
                        break;
                    }
                    // [CASE2] 시작 인덱스와 같은 경우 : 시작 인덱스 오른쪽으로 이동
                    else if (startIdx == i) {
                        startIdx++;
                    }
                    // [CASE3] 종료 인덱스와 같은 경우 : 마지막 인덱스 왼쪽으로 이동
                    else if (endIdx == i) {
                        endIdx--;
                    }
                }
                // [STEP4-2] 시작값과 종료값의 합이 찾는 값보다 작은 경우 : 시작 값을 오른쪽으로 이동
                else if (inputIntArr[startIdx] + inputIntArr[endIdx] < findItem) {
                    startIdx++;
                }
                // [STEP4-3] 시작값과 종료값의 합이 찾는 값보다 큰 경우  : 종료 값을 왼쪽으로 이동
                else {
                    endIdx--;
                }
            }
        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
