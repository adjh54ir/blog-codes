package com.adjh.javaalgorithm.controller.exhaustive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : BfsController
 * @since : 2024. 5. 21.
 */
@RestController
@RequestMapping("/api/v1/exhaustive/bfs")
public class BfsController {

    /**
     * [프로그래머스] level2 - 타겟넘버
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/43165
     * @since 2023.06.24
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int answer = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0, 0)); // (currentSum, index)

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int currentSum = pair.currentSum;
            int index = pair.index;

            if (index == numbers.length) {
                if (currentSum == target) {
                    answer++;
                }
            } else {
                queue.offer(new Pair(currentSum + numbers[index], index + 1)); // 현재 합계 값의 더한 수(+)를 큐에 추가합니다.
                queue.offer(new Pair(currentSum - numbers[index], index + 1)); // 현재 합계 값의 뺀 수(-)를 큐에 추가합니다
            }
        }

        return new ResponseEntity<>(ar, HttpStatus.OK);

    }

    class Pair {
        int currentSum;
        int index;

        public Pair(int currentSum, int index) {
            this.currentSum = currentSum;
            this.index = index;
        }
    }
}
