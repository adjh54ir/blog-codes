package com.adjh.javaalgorithm.controller.exhaustive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 완전탐색 > 비트마스크
 *
 * @author : jonghoon
 * @link: https://adjh54.tistory.com/227
 * @fileName : BitMaskController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/exhaustive/bitMask")
public class BitMaskController {

    /**
     * [프로그래머스] level1 - [1차] 비밀지도
     *
     * @return ResponseEntity
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/17681
     * @since 2023.07.23
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question15() {

        int n = 5;
        int[] arr1 = new int[]{9, 20, 28, 18, 11};
        int[] arr2 = new int[]{30, 1, 21, 17, 28};

        // [STEP1] 결과값을 넣을 배열의 사이즈를 구성합니다.
        String[] answer = new String[n];

        // [STEP2] arr1와 arr2의 비트 연산자로 'OR 연산자' 계산을 하고 이진수로 변환한 값을 결과 배열에 넣어둡니다.(비트마스크 - 이진수로 변환하여 연산합니다)
        for (int i = 0; i < arr1.length; i++) {
            answer[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
            System.out.println(answer[i]);
        }

        // [STEP3] 구성한 이진수 배열을 순회하며 0인경우 " " 공백으로 변환하고 1인 경우 "#"으로 변환합니다.
        for (int i = 0; i < answer.length; i++) {
            answer[i] = String.format("%" + n + "s", answer[i]);            // n 길이에 맞게 문자열을 맞춥니다.
            answer[i] = answer[i].replace("1", "#");    // "1"의 값인 경우 "#"로 치환합니다.
            answer[i] = answer[i].replace("0", " ");    // "0"의 값인 경우 " "로 치환합니다.
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
