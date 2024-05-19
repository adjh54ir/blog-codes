package com.adjh.javaalgorithm.controller.datastruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 자료구조 > 큐
 *
 * @author : jonghoon
 * @link : https://adjh54.tistory.com/185
 * @fileName : QueueController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/datastruct/queue")
public class QueueController {

    /**
     * [프로그래머스] Level1 - 명예의 전당(1)
     *
     * @return ResponseEntity<ApiResponse>
     * @link
     * @since 2023.05.29
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int k = 3;
        int[] score = {10, 100, 20, 150, 1, 100, 200};
        int[] answer = new int[score.length];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // [STEP1] 배열을 순회합니다.
        for (int i = 0; i < score.length; i++) {

            // [STEP2] 우선순위 큐 내의 값을 넣습니다.
            pq.add(score[i]);

            // [STEP3] 우선순위 큐의 사이즈가 k보다 클 경우에만 제일 작은 값을 '제거'합니다.
            if (pq.size() > k) {
                pq.poll();
            }

            // [STEP4] 우선순위 큐에서 제일 작은 값을 '반홥'합니다
            answer[i] = pq.peek();
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] level2 - 기능 개발
     *
     * @return ResponseEntity
     * @link <https://school.programmers.co.kr/learn/courses/30/lessons/42586>
     * @since 2023.05.13
     */
    @PostMapping("/2")
    public ResponseEntity<Object> question2() {
        List<Integer> answerList = new ArrayList<>();
        int[] progresses = new int[]{93, 30, 55};
        int[] speeds = new int[]{1, 30, 5};

        Queue<Integer> queue = new LinkedList<>();

        // [STEP1] 사이즈가 동일하여 하나의 반복문에서 두개를 수행합니다.
        for (int i = 0; i < progresses.length; i++) {
            // [STEP2] 최대 완료 값에서 진행 상태를 빼고 걸리는 시간을 나눕니다.(나머지는 버립니다)
            int result = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            // [STEP3] 요소 벌료 계산된 값을 큐에 더합니다.
            queue.add(result);
        }
        System.out.println("queue : " + queue);         // [7, 3, 9]

        int temp = 0;       // 임시로 넣을 값
        int count = 0;      // 배포 시 기능 개발 count

        // [STEP4] 누적된 값을 기반으로 큐가 비워질때까지 수행합니다.
        while (!queue.isEmpty()) {

            // [STEP5] 큐의 값(가장 먼저 들어온 값)을 빼고 임시 값에 저장합니다.
            temp = queue.poll();
            count = 1;

            // [STEP6] 큐가 비워져 있지 않고 가장 먼저 들어온 값이 다음 들어온 값 보다 큰 경우 루프를 종료합니다.
            while (!queue.isEmpty() && (temp >= queue.peek())) {
                // [STEP7] 현재 큐의 가장 먼저 들어온 값을 빼고 Counting을 합니다.
                queue.poll();
                count++;
            }

            // [STEP8] 최종 카운트 된 값을 리스트에 넣습니다. : 사이즈를 정하지 않아도 되므로 가변 배열을 이용하였습니다.
            answerList.add(count);
        }

        // [STEP9] List to Array 반환
        int answer[] = new int[answerList.size()];
        if (answerList.size() == 0) answer = new int[]{};

        // [STEP10] answer 배열을 구성합니다.
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);

        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


    /**
     * 3. [프로그래머스] level2 - 프로세스
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42587
     * @since 2023.05.
     */
    @PostMapping("/3")
    public ResponseEntity<Object> question3() {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();

        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        // int[] priorities = {1, 1, 9, 1, 1, 1};
        // int location = 0;

        // [STEP1] 우선순위 큐를 생성하고 값을 넣습니다.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int priority : priorities) {
            pq.offer(priority);
        }
        // [CASE2] 우선순위 큐가 비워질때까지 반복됩니다.
        while (!pq.isEmpty()) {

            // [STEP3] 구성한 큐를 순회합니다.
            for (int i = 0; i < priorities.length; i++) {

                // [STEP4] 큐의 요소 값과 큐의 최상위 값(pg.peek)인 우선순위가 높은 요소인 경우를 비교합니다.
                if (priorities[i] == pq.peek()) {

                    // [STEP5] 우선순위 큐에서는 최상위 값을 제거합니다.
                    pq.poll();
                    answer++;

                    // [STEP6] 큐와 위치가 같으면 큐를 초기화하고 멈춥니다.
                    if (i == location) {
                        pq.clear();
                        break;
                    }
                }
            }
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * 4. [프로그래머스] level2- 다리를 지나는 트럭
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42583
     * @since 2023.07.16
     */
    @PostMapping("/4")
    public ResponseEntity<Object> question4() {

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = new int[]{7, 4, 5, 6};

        int answer = 0;

        // [STEP1] Queue를 선언합니다.
        Queue<Integer> queue = new LinkedList<>();

        int sum = 0;    // 트럭이 들어오고 나가는 중에 최대 무게
        int time = 0;   // 트럭이 지나가는데 경과 시간

        // [STEP2] 트럭을 순회합니다
        for (int i = 0; i < truck_weights.length; i++) {

            int truck = truck_weights[i];

            // [STEP3] 하나의 트럭 별 Queue, sum, time에 누적합니다.
            while (true) {

                // [CASE1] Queue 비어져 있는 경우 : 최초 들어온 경우 큐에 트럭을 넣고 트럭에 무게를 합하고 지나간 시간을 올립니다.
                if (queue.isEmpty()) {
                    queue.add(truck);
                    sum += truck;
                    time++;
                    break;
                }

                // [CASE2] Queue 가 가득찬 경우 : 큐의 맨앞의 요소를 제거합니다.(다리에 도착한 요소가 다리를 내림)
                else if (queue.size() == bridge_length) {
                    sum -= queue.poll();
                }
                // [CASE3] Queue 가득차지 않은 경우 : 현재 트럭의 용량을 비교하여 혼자 지나갈지 같이 지나갈지 정한다.
                else {
                    // [CASE3-1] 트럭의 최대 용량이 넘지 않은 경우 : A 트럭 + B 트럭의 최대용량을 넘지 않는 경우(함께 지나간다)
                    if (sum + truck <= weight) {
                        queue.add(truck);
                        sum += truck;
                        time++;
                        break;
                    }
                    // [CASE3-2] 트럭의 최대 용량이 넘는 경우 : 큐에 0의 값을 넣고 트럭이 다리를 지나가게 한다.
                    else {
                        queue.add(0);
                        time++;
                    }
                }
            }
        }

        //[STEP4] 마지막 트럭이 지나는 시간
        answer = time + bridge_length;
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


}
