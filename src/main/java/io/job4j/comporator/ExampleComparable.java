package io.job4j.comporator;

import java.util.*;

public class ExampleComparable {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("C");
        list.add("A");
        list.add("B");

        System.out.println("Before sorting:");
        System.out.println(list);
        Collections.sort(list);
        System.out.println("After sorting:");
        System.out.println(list);
    }
}