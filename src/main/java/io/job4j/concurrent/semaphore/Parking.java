package io.job4j.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class Parking {
    private final Semaphore semaphore = new Semaphore(5); /* 5 парковочных мест */

    public void park() throws InterruptedException {
        semaphore.acquire(); /* уменьшаем счетчик на 1 */
        System.out.println("Автомобиль припаркован");
        /* код для парковки автомобиля */
        Thread.sleep(1000); /* имитация времени парковки */
        semaphore.release(); /* увеличиваем счетчик на 1 */
        System.out.println("Автомобиль уехал");
    }

    public static void main(String[] args) {
        Parking parking = new Parking();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    parking.park();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}