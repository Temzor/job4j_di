package io.job4j.concurrent.deadlock;

public class DeadlockBetweenObjects {
    static class Resource {
        private final String name;

        Resource(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Resource resource1 = new Resource("Resource1");
        Resource resource2 = new Resource("Resource2");

        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked " + resource1.getName());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Thread 1: Waiting for " + resource2.getName());
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked " + resource2.getName());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked " + resource2.getName());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Thread 2: Waiting for " + resource1.getName());
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked " + resource1.getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
