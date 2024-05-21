package com.adjh.javaalgorithm.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 탐색 알고리즘 > 해시 알고리즘
 *
 * @author : jonghoon
 * @link : https://adjh54.tistory.com/490
 * @fileName : SearchHashController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/search/hash")
public class HashSearchController {

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
     * [프로그래머스] Level1 - 폰켓몬
     *
     * @return
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/1845
     */
    @PostMapping("/1")
    public ResponseEntity<Object> question1() {
        int answer = 0;
        int[] nums = {3, 1, 2, 3};
        int[] nums2 = {3, 1, 2, 3};
        int[] nums3 = {3, 1, 2, 3};


        // key(폰켓몬 번호), value(폰켓몬 번호 별 개수)
        Map<Integer, Integer> resultMap = new HashMap<>();

        // 폰켓몬 번호 별 개수를 Map 요소 값에 추가합니다.
        for (int num : nums) {
            resultMap.put(num, resultMap.containsKey(num) ? resultMap.get(num) + 1 : 1);
            System.out.println(resultMap.toString());
        }
        // 폰켓몬의 종류 별 개수
        answer = resultMap.size();

        // 최대로 얻을수 있는 폰켓몬 개수
        int obtainCnt = Math.abs(nums.length / 2);

        // 폰켓몬의 종류 보다 얻을 수 있는 개수가 많은 경우 폰켓몬 종류 반환
        // 폰켓몬의 종류 보다 얻을 수 있는 개수가 적은 경우 얻을 수 있는 개수 반환
        answer = answer < obtainCnt ? answer : obtainCnt;


        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] Level1 - 완주하지 못한 선수
     *
     * @return
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42576
     */
    @PostMapping("/2")
    public ResponseEntity<Object> question2() {
        String answer = "";
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};

        Map<String, Integer> pHashMap = new HashMap<>();

        // Map 내에 참가자 목록(participant)을 추가합니다. 참가자 이름(key), 참가여부 체크(value) - 0: 참가, 1: 미 참가
        for (String participantItem : participant) {
            pHashMap.put(participantItem, pHashMap.getOrDefault(participantItem, 0) + 1);
        }

        // 완주 한 인원을 체크해서 참가여부에 따라 참가여부 체크(value)를 -1로 참가 체크를 수행합니다.
        for (String completionItem : completion) {
            pHashMap.put(completionItem, pHashMap.get(completionItem) - 1);
        }

        // Map을 순회하면서 0이 아닌 값을 찾아서 결과로 반환합니다.
        for (Map.Entry<String, Integer> entry : pHashMap.entrySet()) {
            if (entry.getValue() != 0) {
                answer = entry.getKey();
            }
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] level2 - 전화번호 목록
     *
     * @return
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42577
     */
    @PostMapping("/3")
    public ResponseEntity<Object> question3() {
        boolean answer = true;

//        String[] phone_book = {"119", "97674223", "1195524421"};
        String[] phone_book = {"123", "456", "789"};

        Map<Integer, Object> resultMap = new HashMap<>();
        // 1. List 값을 HashMap에 넣습니다. (key : index, value: 요소)
        for (int i = 0; i < phone_book.length; i++) {
            resultMap.put(i, phone_book[i]);
        }
        // 2. List를 순회합니다. : List 요소 출력
        for (int i = 0; i < phone_book.length; i++) {
            // 3. 요소의 길이만큼 순회합니다 : 단어 출력
            for (int j = 0; j < phone_book[i].length(); j++) {
                // 4. Map의 요소와 List 내의 요소의 단어들의 접두사를 비교하여 동일하다면 false 값 반환
                if (resultMap.containsValue(phone_book[i].substring(0, j))) {
                    answer = false;
                }
            }
        }


        System.out.println("initInt :: " + resultMap);


        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
