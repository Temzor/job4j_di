package io.job4j.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.List;
import java.util.ArrayList;

public class ProducerConsumerExchanger {
    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        List<Integer> initialEmptyBuffer = new ArrayList<>();
        List<Integer> initialFullBuffer = new ArrayList<>();

        Producer producer = new Producer(exchanger, initialEmptyBuffer);
        Consumer consumer = new Consumer(exchanger, initialFullBuffer);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {
    private List<Integer> buffer;
    private final Exchanger<List<Integer>> exchanger;

    Producer(Exchanger<List<Integer>> exchanger, List<Integer> buffer) {

        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    public void run() {
        int data = 0;
        try {
            while (true) {
                /* Заполняем буфер данными */
                for (int i = 0; i < 5; i++) {
                    data++;
                    System.out.println("Производитель произвел: " + data);
                    buffer.add(data);
                }
                /* Обмениваем заполненный буфер на пустой */
                System.out.println("Производитель ожидает обмена буфером...");
                buffer = exchanger.exchange(buffer);
                System.out.println("Производитель получил пустой буфер");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private List<Integer> buffer;
    private final Exchanger<List<Integer>> exchanger;

    Consumer(Exchanger<List<Integer>> exchanger, List<Integer> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                /* Обмениваем пустой буфер на заполненный */
                System.out.println("Потребитель ожидает обмена буфером...");
                buffer = exchanger.exchange(buffer);
                System.out.println("Потребитель получил заполненный буфер");

                /* Потребляем данные из буфера */
                for (int data : buffer) {
                    System.out.println("Потребитель потребил: " + data);
                }
                buffer.clear(); /* Очищаем буфер после потребления */
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
