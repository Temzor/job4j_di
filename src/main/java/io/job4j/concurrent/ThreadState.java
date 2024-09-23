package io.job4j.concurrent;

public class ThreadState {
    @SuppressWarnings("checkstyle:ParenPad")
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            Thread.currentThread().setName("first thread");
            System.out.println(Thread.currentThread().getName());
        });

        Thread second = new Thread(() -> {
            Thread.currentThread().setName("second thread");
            System.out.println(Thread.currentThread().getName());
        });

        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " " + first.getState());
            System.out.println(second.getName() + " " + second.getState());
        }
        System.out.printf("%s %s", Thread.currentThread().getName(), "Работа завершена");
    }
}