package io.job4j.concurrent.threadlocaldemo;

import lombok.SneakyThrows;

public class ThreadLocalDemo {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @SneakyThrows
    public static void main(String[] args) {
        Thread firstThread = new FirstThread();
        Thread secondThread = new SecondThread();
        threadLocal.set("Это поток main.");
        System.out.println(threadLocal.get());
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
    }
}