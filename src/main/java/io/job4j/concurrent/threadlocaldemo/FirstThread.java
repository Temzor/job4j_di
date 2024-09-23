package io.job4j.concurrent.threadlocaldemo;

public class FirstThread extends Thread {
    @Override
    public void run() {
       ThreadLocalDemo.threadLocal.set("Thread 1");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
