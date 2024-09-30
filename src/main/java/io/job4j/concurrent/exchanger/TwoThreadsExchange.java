package io.job4j.concurrent.exchanger;

import java.util.concurrent.Exchanger;

public class TwoThreadsExchange {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread threadA = new Thread(() -> {
            String message = "Сообщение от потока A";
            try {
                /* Обмен сообщениями с потоком B */
                String response = exchanger.exchange(message);
                System.out.println("Поток A получил: " + response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            String message = "Сообщение от потока B";
            try {
                /* Обмен сообщениями с потоком A */
                String response = exchanger.exchange(message);
                System.out.println("Поток B получил: " + response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
    }
}