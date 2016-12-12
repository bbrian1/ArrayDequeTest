package com.company;

import java.math.BigInteger;
import java.util.Deque;

/**
 * Created by brianb on 12/10/16.
 */
public class DequeTester<T> {

    private final T[] testData;
    private final int iterations;
    Deque<T> deque;

    public DequeTester(Deque<T> deque, T[] testData, int iterations){
        this.deque = deque;
        this.testData = testData;
        this.iterations = iterations;
    }

    public TestResults runTest(){
        long maxTime = Long.MIN_VALUE;
        long minTime = Long.MAX_VALUE;
        BigInteger totalTime = BigInteger.ZERO;

        for(int i = 0; i <= iterations; i++){
            //System.out.println("---------------------------");
            //System.out.printf("Running iteration %d of %d%n", i, iterations);
            long result = individualTest();
            maxTime = Math.max(result, maxTime);
            minTime = Math.min(result, minTime);
            totalTime = totalTime.add(BigInteger.valueOf(result));
            //System.out.printf("Runtime: %d ms%n", result);
        }

        long avgTime = totalTime.divide(BigInteger.valueOf(iterations)).longValue();

        return new TestResults(minTime, maxTime, avgTime);
    }

    private long individualTest(){
        long now = System.currentTimeMillis();
        for(T obj : testData){
            deque.addLast(obj);
        }

        while(!deque.isEmpty()){
            deque.removeFirst();
        }

        return System.currentTimeMillis() - now;
    }
}
