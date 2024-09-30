package io.job4j.concurrent.synchronizers;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        Thread worker1 = new Thread(new Worker(latch), "Worker-1");
        Thread worker2 = new Thread(new Worker(latch), "Worker-2");
        Thread worker3 = new Thread(new Worker(latch), "Worker-3");

        worker1.start();
        worker2.start();
        worker3.start();

        System.out.println("Main thread is waiting for workers to finish...");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All workers have finished. Main thread is proceeding.");
    }
}

class Worker implements Runnable {
    private final CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is working...");
            Thread.sleep((long) (Math.random() * 3000));

            System.out.println(Thread.currentThread().getName() + " has finished work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}