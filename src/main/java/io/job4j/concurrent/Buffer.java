package io.job4j.concurrent;

public class Buffer {
    private final StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        /* Монитором является каждый объект класса Buffer */
        /* Это начало критической секции */
        System.out.print(value);
        buffer.append(value);
        /* Это конец критической секции */
    }

    @Override

    public synchronized String toString() {
        /* Монитором является каждый объект класса Buffer */
        /* Это начало критической секции */
        return buffer.toString();
        /* Это конец критической секции */
    }
}