package io.job4j.concurrent.deadlock;

public class DynamicLockOrderDeadlock {
    static class Resource {}

    private static void acquireLocks(Resource r1, Resource r2) {
        synchronized (r1) {
            System.out.println(Thread.currentThread().getName() + ": Заблокировал " + r1);
            try { Thread.sleep(50); } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (r2) {
                System.out.println(Thread.currentThread().getName() + ": Заблокировал " + r2);
            }
        }
    }

    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        Thread thread1 = new Thread(() -> acquireLocks(resource1, resource2), "Поток 1");
        Thread thread2 = new Thread(() -> acquireLocks(resource2, resource1), "Поток 2");

        thread1.start();
        thread2.start();
    }
}
