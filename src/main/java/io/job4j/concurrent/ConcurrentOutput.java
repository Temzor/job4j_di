package io.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            Thread.currentThread().setName("first");
            System.out.println(Thread.currentThread().getName());
        });

        Thread second = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Thread.currentThread().setName("second");
                        System.out.println(Thread.currentThread().getName());
                    }
                }
        );

        second.start();
        first.start();
        System.out.println(Thread.currentThread().getName());
    }
}