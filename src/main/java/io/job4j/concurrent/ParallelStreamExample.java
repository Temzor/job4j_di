package io.job4j.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);


        Stream<Integer> stream = list.parallelStream();
        System.out.println(stream.isParallel());


        Optional<Integer> multiplication = stream
                .reduce((left, right) -> left * right);

        System.out.println(multiplication.get());


        list.stream().parallel().peek(System.out::println).toList();
        System.out.println("/***************************************************/");
        list.stream().parallel().forEach(System.out::println);
        System.out.println("/***************************************************/");
        list.stream().parallel().forEachOrdered(System.out::println);
    }
}