package io.job4j.concurrent.countdownlatch;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class Friends {
    private final CountDownLatch latch = new CountDownLatch(5); /* 5 друзей */

    public void ready()  {
        System.out.println("Друг готов");
        latch.countDown(); /* уменьшаем счетчик на 1 */
    }

    public void goCinema() throws InterruptedException {
        latch.await();
        System.out.println("Все друзья готовы, идем в кино!");
    }

    public static void main(String[] args) throws InterruptedException {
        Friends friends = new Friends();
        for (int i = 0; i < 5; i++) {
            new Thread(friends::ready).start();
        }
        friends.goCinema();
    }
}