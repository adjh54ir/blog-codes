package com.adjh.javaalgorithm.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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


        // [STEP1] 폰켓몬 번호 별로 갯수를 저장하는 해시 맵을 구성합니다 : 폰켓몬 번호(key), 폰켓몬 번호 별 개수(value)
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int num : nums) {
            // [STEP2] 해시 맵 내에 구성할때, 동일한 키 값이 존재하면(getOrDefault) 값을 1 더 해줍니다.
            resultMap.put(num, resultMap.getOrDefault(num, 0) + 1);
        }
        System.out.println("resultMap :: " + resultMap);    // {1=1, 2=1, 3=2}

        // [STEP3] 폰켓몬의 종류 별 개수와 최대로 얻을 수 있는 폰켓몬 개수를 구합니다.
        answer = resultMap.size();
        int obtainCnt = Math.abs(nums.length / 2);

        // [STEP4] 결과값을 반환합니다.
        // 1. 폰켓몬의 종류 보다 얻을 수 있는 개수가 많은 경우 => 폰켓몬 종류 반환
        // 1. 폰켓몬의 종류 보다 얻을 수 있는 개수가 적은 경우 => 얻을 수 있는 개수 반환
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

        // [STEP1] 리스트트 해시 셋으로 구성합니다 : 요소를 해시 셋에 넣습니다.
        // 해시 맵을 사용하는것보다 해시셋은 해시함수를 통해 중복확인을 하여 빠른 검색을 수행합니다.
        HashSet<String> hashSet = new HashSet<>();
        for (String phoneBookItem : phone_book) {
            hashSet.add(phoneBookItem);
        }
        System.out.println("hashset :: " + hashSet);    // [123, 456, 789]

        // [STEP2] 리스트를 전체 순회하여 요소를 출력 합니다
        for (String phoneBookItem : phone_book) {
            // [STEP3] 요소의 길이만큼 순회합니다. : 단어 출력
            for (int j = 0; j < phoneBookItem.length(); j++) {
                // [STEP4] 해시맵의 값과 단어를 하나씩 쪼개가며 접두사를 비교합니다 : 같을 경우 false 값 반환
                if (hashSet.contains(phoneBookItem.substring(0, j))) {
                    answer = false;
                    break;
                }
            }
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] level2 : 의상
     *
     * @return
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42578
     */
    @PostMapping("/4")
    public ResponseEntity<Object> question4() {
        int answer = 1;
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        // [STEP1] 2차원 배열을 HashMap 형태로 구성합니다 : 옷 종류(key), 옷 개수(Value)
        Map<String, Integer> resultMap = new HashMap<>();
        for (String[] cloth : clothes) {
            // [STEP2] 해시 맵을 구성할때, 동일한 키 값이 존재하면(getOrDefault) 값을 1 더 해줍니다.
            resultMap.put(cloth[1], resultMap.getOrDefault(cloth[1], 0) + 1);
        }
        System.out.println("resultMap :: " + resultMap); // resultMap :: {eyewear=1, headgear=2}

        // [STEP3] 해시 맵을 순회합니다.
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            // [STEP4] 값에 1을 더하여 누적하여 곱해줍니다. : 중복되지 않는 키(옷 종류)의 합
            answer *= entry.getValue() + 1;
        }

        // [STEP5] 아무것도 입지 않은 경우를 제외합니다.
        answer -= 1;


        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] level3 : 베스트 앨범
     *
     * @return
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42579
     */
    @PostMapping("/5")
    public ResponseEntity<Object> question5() {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        List<Integer> answerList = new ArrayList<>();


        // [STEP1] 배열을 해시 맵으로 변환합니다.
        Map<String, Integer> totalGenresMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            // [STEP2] 장르 별(genres) 플레이(plays) 횟수의 합을 요소로 추가합니다. : 동일한 장르를 묶어서 합합니다.
            totalGenresMap.put(genres[i], totalGenresMap.getOrDefault(genres[i], 0) + plays[i]);
        }
        System.out.println("totalGenresMap :: " + totalGenresMap);          // {pop=3100, classic=1450}

        // [STEP3] 장르 별(genres) 플레이(plays) 합이 큰 순서(내림차순)로 정렬 합니다.
        List<String> sortedList = new ArrayList<>(totalGenresMap.keySet());
        Collections.sort(sortedList, (v1, v2) -> totalGenresMap.get(v2).compareTo(totalGenresMap.get(v1)));
        System.out.println("정렬 리스트 :" + sortedList);    // ["pop", "classic"]


        // [STEP4] 플레이 합이 큰 리스트 순서대로 순회 합니다.
        for (String listItem : sortedList) {

            // [STEP5] 맵 내에 인덱스(key), 플레이(play)를 저장합니다. : 이를 통해 장르 별 순서를 정합니다.
            Map<Integer, Integer> genresMap = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (listItem.equals(genres[i])) {
                    genresMap.put(i, plays[i]);
                }
            }
            System.out.println("genresMap :: " + genresMap); // {1=600, 4=2500},  {0=500, 2=150, 3=800}

            // [STEP6] 장르 별 순서 맵을 리스트로 변환하여 플레이 값(value)에 따라 정렬을 수행합니다.
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(genresMap.entrySet());
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            System.out.println("list ::" + list); // [4=2500, 1=600], [3=800, 0=500, 2=150]

            // [STEP7] 정렬된 리스트를 풀어 순서대로 값을 가져와 리스트에 담아서 최종 index를 구성합니다. (최대 장르별 2건만 들어갈 수 있음)
            int itemCnt = 0;
            for (Map.Entry<Integer, Integer> entry : list) {
                if (itemCnt < 2) {
                    itemCnt += 1;
                    answerList.add(entry.getKey());
                    System.out.println("itemCnt :: " + itemCnt);
                }
            }
        }

        // [STEP8] 리스트를 배열로 구성하여 최종 결과값을 출력합니다.
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) answer[i] = answerList.get(i);

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] ? - 음식주문
     *
     * @return
     * @link ?
     */
    @PostMapping("/6")
    public ResponseEntity<Object> question6() {
        int answer = 0;
        String[] orders = {"alex pizza pasta", "alex pizza pizza", "alex noodle", "bob pasta", "bob noodle sandwich pasta", "bob steak noodle"};

        Map<String, HashSet<String>> map = new HashMap<>();

        // [STEP1] 주문 배열을 순회합니다.
        for (String order : orders) {

            // [STEP2] 주문 배열 중 이름을 추출합니다.
            String[] splitOrder = order.split(" ");
            String customer = splitOrder[0]; // 고객 이름


            // [STEP3] Hashset 내에 값이 존재하는지 확인합니다.
            HashSet<String> menuSet = map.get(customer);
            // [STEP4] HashSet이 존재하지 않는 경우 : null 값을 넣지 않기 위해 초기화 수행하여 값을 넣지 않습니다.
            if (menuSet == null) {
                menuSet = new HashSet<>();
                map.put(customer, menuSet);
            }
            // [STEP5] 주문 내용에 대해 HashSet에 중복을 제외하여 주문 내용을 넣습니다.
            for (int i = 1; i < splitOrder.length; i++) {
                menuSet.add(splitOrder[i]); // 메뉴 추가
            }
        }
        List<String> answerList = new ArrayList<>();

        int maxVal = 0;
        // [STEP6] 구성된 Map을 순회하면서 출력하며 최대 주문한 사용자를 구합니다.
        for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {

            int orderCnt = entry.getValue().size();

            if (maxVal < orderCnt) {
                maxVal = orderCnt;
                if (answerList.size() > 0) {
                    answerList = new ArrayList<>();
                    answerList.add(entry.getKey());
                }
            }
        }
        System.out.println("result :: " + answerList);

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


}
