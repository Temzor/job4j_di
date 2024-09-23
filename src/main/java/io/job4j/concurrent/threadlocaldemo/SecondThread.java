package io.job4j.concurrent.threadlocaldemo;

public class SecondThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.threadLocal.set("Thread 2");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}