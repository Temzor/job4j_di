package io.job4j.concurrent.ciclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    /* Создаём CyclicBarrier для 3 потоков */
    private static final int NUMBER_OF_WORKERS = 2;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_WORKERS, new Runnable() {
        @Override
        public void run() {
            /* Это действие будет выполнено, когда все потоки достигнут барьера */
            System.out.println("Все сотрудники завершили этап. Можно переходить к следующему.");
        }
    });

    public static void main(String[] args) {
        /* Запускаем 3 потока */
        for (int i = 1; i <= NUMBER_OF_WORKERS; i++) {
            Thread worker = new Thread(new Worker(i));
            worker.start();
        }
    }

    static class Worker implements Runnable {
        private final int workerId;

        public Worker(int workerId) {
            this.workerId = workerId;
        }

        @Override
        public void run() {
            try {
                /* Этап 1 */
                performTask("Этап 1");
                barrier.await(); /* Ждём, пока все не завершат Этап 1 */

                /* Этап 2 */
                performTask("Этап 2");
                barrier.await(); /* Ждём, пока все не завершат Этап 2 */

                /* Этап 3 */
                performTask("Этап 3");
                barrier.await(); /* Ждём, пока все не завершат Этап 3 */

                System.out.println("Сотрудник " + workerId + " завершил все этапы.");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void performTask(String stage) throws InterruptedException {
            System.out.println("Сотрудник " + workerId + " выполняет " + stage);
            /* Симулируем время выполнения задачи */
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println("Сотрудник " + workerId + " завершил " + stage);
        }
    }
}