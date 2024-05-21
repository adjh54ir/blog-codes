package com.adjh.javaalgorithm.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 탐색 알고리즘 > 이진 탐색 알고리즘
 *
 * @author : jonghoon
 * @link : https://adjh54.tistory.com/187
 * @fileName : SearchBinaryController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/search/binary")
public class BinarySearchController {

    /**
     * 이분탐색
     *
     * @param arr {int[]}: 전체 배열
     * @param key {int}: 찾고자 하는 요소
     * @return ResponseEntity<ApiResponse>
     * @link
     * @since 2023.05.
     */
    @GetMapping("/1")
    public ResponseEntity<Object> binSearchCourse(@RequestParam int[] arr, @RequestParam int key) {
        int answer = 0;
        // [STEP1] 시작 인덱스와 마지막 인덱스 값을 지정합니다.
        int low = 0;
        int high = arr.length - 1;

        // [STEP2] 마지막 인덱스를 보다 첫번째 인덱스가 같아지거나 작을 경우까지 순회합니다.
        while (low <= high) {

            // [STEP3] 중간 값을 구합니다.
            int mid = (low + high) / 2;

            // [CASE4-1] 중간 값보다 찾으려는 값(key)가 큰 경우 : 중간 값에 1을 더하여 오른쪽 절반을 탐색합니다.
            if (arr[mid] < key) {
                low = mid + 1;
            }
            // [CASE4-2] 중간 값보다 찾으려는 값(key)가 작은 경우 : 중간값에 1을 빼서 왼쪽 절반을 탐색합니다.
            else if (arr[mid] > key) {
                high = mid - 1;
            }
            // [CASE4-3] 해당 경우가 아니면 중간값을 최종 값으로 반환합니다.
            else {
                answer = mid;
            }
        }
        // [STEP5] 최종 탐색을 하지 못할 경우 -1을 반환합니다.
        if (answer == 0) answer = -1;

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
