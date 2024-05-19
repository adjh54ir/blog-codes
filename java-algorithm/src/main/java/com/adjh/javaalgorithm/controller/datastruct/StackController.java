package com.adjh.javaalgorithm.controller.datastruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;

/**
 * 자료구조 > 스텍
 *
 * @author : jonghoon
 * @link : https://adjh54.tistory.com/185
 * @fileName : StackController
 * @since : 5/19/24
 */
@RestController
@RequestMapping("/api/v1/datastruct/stack")
public class StackController {

    /**
     * [프로그래머스] Level1 - 같은 수는 싫어
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/12906
     * @since 2023.05.13
     */
    @PostMapping("/1")
    public ResponseEntity<Object> questionStack1() {
        int[] answer;

        int[] arr = {1, 1, 3, 3, 0, 1, 1};
//        int[] arr = {4,4,4,3,3};	//[4,3]

        Stack<Integer> stack = new Stack<>();

        // [CASE1] 스택 내의 값이 존재하지 않으면 요소를 채워넣고 존재하면 스텍의 peek()로 가장 위에 값을 가져와 비교합니다.
        for (int arrItem : arr) {
            if (stack.isEmpty()) stack.push(arrItem);
            else if (stack.peek() != arrItem) stack.push(arrItem);
        }
        answer = new int[stack.size()];

        // [CASE2] Stack to Array : 배열을 역순으로 순회하면서 스텍의 pop()을 이용하여 마지막 값을 넣습니다.
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] Level2 - 올바른 괄호
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/12909
     * @since 2023.05.13
     */
    @PostMapping("/2")
    public ResponseEntity<Object> questionStack2() {
        boolean answer = true;
        String s = "()()";
//        String s = "(())()";
//        String s = "(()(";

        Stack<Character> stack = new Stack<>();

        // [STEP1] 문자를 순회합니다.
        for (char iItem : s.toCharArray()) {

            // [CASE1] 요소 값이 좌측 괄호인 경우 Stack에 넣어둡니다.
            if (iItem == '(') stack.push(iItem);

                // [CASE2] 요소 값이 우측 괄호인 경우 Stack에 값이 있는 경우 뺍니다.(좌측 괄호 값을 뺍니다)
            else {
                if (stack.isEmpty()) answer = false;
                else stack.pop();
            }
        }

        // [STEP2] Stack의 값이 비어있지 않은 상태(짝이 이루어지지 않은 상태)라면 false로 리턴합니다.
        if (!stack.isEmpty()) answer = false;

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * [프로그래머스] Level2 - 괄호 회전하기
     *
     * @return ResponseEntity<ApiResponse>
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/76502?language=java
     * @since 2023.05.
     */
    @PostMapping("/3")
    public ResponseEntity<Object> question3() {
        int answer = 0;
        String s = "[](){}";        // 3
//        String s = "}]()[{";      // 2
//        String s = "[)(]";          // 0
//        String s = "}}}";         // 0

        if (chkBracket(s)) {
            answer += 1;
        }
        // [STEP1] 문자열의 길이 만큼 순회합니다. : 1번 인덱스부터 시작합니다.
        for (int i = 1; i < s.length(); i++) {

            // [STEP2] 괄호 회전(왼쪽 회전) : 첫번째 문자열을 제외한 문자열과 첫번째 문자열의 문자를 조합합니다.
            s = s.substring(1) + s.charAt(0);

            // [STEP3] 왼쪽으로 회전한 문자열의 짝이 맞는지 여부
            if (chkBracket(s)) answer += 1;

        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * 괄호 쌍 확인 : 전달받은 문자열을 기반으로
     *
     * @return
     */
    private boolean chkBracket(String s) {
        Stack<Character> cStack = new Stack<>();

        // [STEP1] 괄호 문자열을 문자 단위로 순회합니다.
        for (char sItem : s.toCharArray()) {

            // [CASE1] '왼쪽 괄호'일 경우는 Stack 추가
            if (sItem == '{' || sItem == '[' || sItem == '(') {
                cStack.push(sItem);
            }
            // [CASE2] '오른쪽 괄호'일 경우는 짝을 찾아서 Stack에서 제외합니다.
            else {
                // [STEP2-1] Stack 비어있지 않은 경우  => 각각 괄호에 맞는 값을 뺀다.
                if (!cStack.isEmpty()) {
                    switch (sItem) {
                        case '}':
                            if (cStack.peek() == '{') cStack.pop();
                            break;
                        case ']':
                            if (cStack.peek() == '[') cStack.pop();
                            break;
                        case ')':
                            if (cStack.peek() == '(') cStack.pop();
                            break;
                    }
                    // [STEP2-1] Stack 비어있는 경우 => 해당 값을 넣습니다
                } else cStack.push(sItem);
            }
        }
        return cStack.isEmpty();
    }

    /**
     * [프로그래머스] Level2 - 주식가격
     *
     * @return ResponseEntity<ApiResponse>
     * @link <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42584">...</a>
     * @since 2023.07.14
     */
    @PostMapping("/4")
    public ResponseEntity<Object> question04() {

        int[] prices = new int[]{1, 2, 3, 2, 3};
        int[] answer = new int[prices.length];

        // [STEP1] 스택 객체를 생성합니다.
        Stack<Integer> stack = new Stack<>();

        // [STEP2] 주식 가격을 순회하면서 가격이 떨어진 '초'에 대한 값을 찾습니다.
        for (int i = 0; i < prices.length; i++) {

            // [STEP3] Stack 비어져 있지 않고, 현재 값과 비교하여 이후 값이 더 큰 경우 : 답을 계산하여 결과 배열에 누적합니다.
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();    // 답이 구해집니다.
                stack.pop();                                // Stack에 최상위에 있는 값을 뺍니다.
            }
            // [STEP4] Stack에는 index를 추가하여 증감합니다.
            stack.push(i);
        }

        // [STEP5] 값을 구하지 못한 경우 : 가격이 끝까지 떨어지지 않은 경우
        while (!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


}
