package com.adjh.springbootasync;

import lombok.ToString;

import java.util.concurrent.RecursiveTask;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : Fibonacci
 * @since : 8/15/24
 */
public class Fibonacci extends RecursiveTask<Integer> {
    private final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        System.out.println("Thread: " + Thread.currentThread().getName() + " computing Fibonacci(" + n + ")");
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        int result = f2.compute() + f1.join();
        return result;
    }
}
