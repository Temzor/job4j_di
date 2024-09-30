package io.job4j.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerTimeoutExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread threadC = new Thread(() -> {
            String message = "Сообщение от потока C";
            try {
                System.out.println("Поток C ожидает обмена...");
                /* Ждем максимум 2 секунды */
                String response = exchanger.exchange(message, 2, TimeUnit.SECONDS);
                System.out.println("Поток C получил: " + response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("Поток C: Время ожидания истекло");
            }
        });

        Thread threadD = new Thread(() -> {
            String message = "Сообщение от потока D";
            try {
                /* Усыпляем поток на 3 секунды, чтобы вызвать таймаут в потоке C */
                Thread.sleep(3000);
                System.out.println("Поток D ожидает обмена...");
                String response = exchanger.exchange(message);
                System.out.println("Поток D получил: " + response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadC.start();
        threadD.start();
    }
}