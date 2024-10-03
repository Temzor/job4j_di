package io.job4j.concurrent.task;

import java.util.*;

/**
 * Should be improved to reduce calculation time.
 * <p>
 * 1. Change this file or create new one using parallel calculation mode.
 * 2. Do not use executors, only plain threads  (max threads count which should be created for calculations is com.fitechsource.test.TestConsts#MAX_THREADS)
 * 3. Try to provide simple solution, do not implement frameworks.
 * 4. Don't forget that calculation method can throw exception, process it right way.
 * (Stop calculation process and print error message. Ignore already calculated intermediate results, user doesn't need it.)
 * <p>
 * Please attach code files to email - skhisamov@fitechsource.com
 */

public class TestParallel {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        parallelThreaded();

        long refValueTime = System.currentTimeMillis() - start;
        System.out.println("Execution time is: " + refValueTime + " ms.");
    }

    private static void parallelThreaded() throws InterruptedException {
        Set<Double> res = Collections.synchronizedSet(new HashSet<>());
        Thread[] threads = new Thread[TestConsts.MAX_THREADS];

        record Threads(Set<Double> res,
                       Integer count) implements Runnable {

            @Override
            public void run() {
                try {

                    res.addAll(TestCalc.calculate(this.count));

                } catch (Exception e) {
                    System.out.println("Error, see log.");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
            threads[i] = new Thread(new Threads(res, TestConsts.N));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Count N data: " + res.size());
        System.out.println(res);
    }
}