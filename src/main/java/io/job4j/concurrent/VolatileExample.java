package io.job4j.concurrent;

public class VolatileExample {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            while (running) {
                System.out.println("Thread is running...");
                try {
                    Thread.sleep(1000); /* имитируем работу */
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted");
                }
            }
            System.out.println("Thread stopped");
        });

        worker.start();

        /* Остановить поток через 3 секунды */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        running = false; /* установка флага для остановки потока*/
        System.out.println("Main thread set running to false");
    }
}
