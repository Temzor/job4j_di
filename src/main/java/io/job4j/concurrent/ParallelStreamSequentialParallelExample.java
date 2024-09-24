package io.job4j.concurrent;

import java.util.stream.IntStream;

public class ParallelStreamSequentialParallelExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(0, 100).parallel();
        System.out.println(intStream.isParallel());

        IntStream sequential = intStream.sequential();
        System.out.println(sequential.isParallel());

        IntStream parallel = sequential.parallel();
        System.out.println(parallel.isParallel());
    }
}
