package io.job4j.concurrent.atomacity;

public class Count {
    private int value;

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}