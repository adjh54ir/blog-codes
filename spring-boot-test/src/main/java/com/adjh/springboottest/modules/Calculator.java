package com.adjh.springboottest.modules;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : Calculator
 * @since : 7/28/24
 */
public class Calculator {
    private Double result;

    /**
     * 존재하지 않으면 0의 값을 반환
     */
    public Calculator() {
        this.result = 0.0;
    }

    public Calculator(Double result) {
        this.result = result;
    }


    /**
     * 덧셈
     *
     * @param number
     * @return
     */
    public Calculator add(Double number) {
        this.result += number;
        return this;
    }

    /**
     * 뺄셈
     *
     * @param number
     * @return
     */
    public Calculator sub(Double number) {
        this.result -= number;
        return this;
    }

    /**
     * 나눗셈
     *
     * @param number
     * @return
     */
    public Calculator div(Double number) {

        if (number == 0.0) throw new ZeroDivisionException();

        this.result /= number;
        return this;
    }

    /**
     * 곱셈
     *
     * @param number
     * @return
     */
    public Calculator mul(Double number) {
        this.result *= number;
        return this;
    }

    public Double getResult() {
        return this.result;
    }


    public static class ZeroDivisionException extends RuntimeException {

    }

}
