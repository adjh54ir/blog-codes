package com.adjh.javaalgorithm.controller.exhaustive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 탐색 알고리즘 > 비선형 구조 탐색 > 깊이 우선 탐색(DFS)
 *
 * @author : lee
 * @link : https://adjh54.tistory.com/328
 * @fileName : DfsController
 * @since : 2024. 5. 21.
 */
@RestController
@RequestMapping("/api/v1/exhaustive/dfs")
public class DfsController {

    static boolean[] visited;
    static int max = 0;

    /**
     * [프로그래머스] level2 - 피로도
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/87946
     * @since 2023.06.24
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int answer = 0;
        int k = 80;
        // 최소 피로도, 소모 피로도
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};

        // [STEP1] visited 방문한 방을 체크합니다.(던전과 동일한 사이즈로 지정합니다)
        visited = new boolean[dungeons.length];

        // [STEP2] dfs 함수에 값들을 전달합니다. (* cnt: 방에 방문한 횟수를 체크합니다)
        dfs(k, dungeons, 0);
        answer = max;
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * 던전의 방을 방문하며 Counting 수행합니다.
     *
     * @param k        현재 피로도
     * @param dungeons 던전 정보
     * @param cnt      방에 방문한 횟수
     */
    void dfs(int k, int[][] dungeons, int cnt) {

        // [STEP1] 모든 던전을 순회합니다.
        for (int i = 0; i < dungeons.length; i++) {
            int visitFatigue = dungeons[i][0];      // 방문에 필요한 최소 필요도
            int recoverFatigue = dungeons[i][1];    // 방문후 회복되는 피로도

            // [STEP2] 기존에 방문 확인, 최소 피로도 확인
            if (visited[i] || k < visitFatigue) {
                continue;
            }

            // [STEP3] 방문함을 지정합니다.
            visited[i] = true;

            // [STEP4] 재귀적으로 현재 피로도를 제외하여 호출합니다.
            dfs(k - recoverFatigue, dungeons, cnt + 1);
            // 다른 케이스를 위해 방문 취소
            visited[i] = false;
        }
        // 최대 카운트를 계산합니다.
        max = Math.max(max, cnt);
    }

    /**
     * [프로그래머스] level2 - 타켓넘버
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/43165
     * @since 2023.06.24
     */
    @PostMapping("/2")
    public ResponseEntity<Object> question2() {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int answer = dfs2(numbers, target, 0, 0); // DFS 탐색 결과를 answer 변수에 저장합니다.
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    private int dfs2(int[] numbers, int target, int currentSum, int index) {
        if (index == numbers.length) {
            if (currentSum == target) {
                return 1;
            } else {
                return 0;
            }
        }

        int cnt = 0;
        cnt += dfs2(numbers, target, currentSum + numbers[index], index + 1); // 현재 합계 값에 더한 수(+)로 DFS 탐색합니다.
        cnt += dfs2(numbers, target, currentSum - numbers[index], index + 1); // 현재 합계 값에 뺀 수(-)로 DFS 탐색합니다.

        return cnt;
    }

}
