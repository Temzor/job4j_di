package io.job4j.concurrent.racecondition;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class RaceConditionExampleTrue {
    @GuardedBy("this")
    private final int number = 0;

    public static void main(String[] args) throws InterruptedException {
        RaceConditionExampleTrue raceCondition = new RaceConditionExampleTrue();
        Thread one = new Thread(raceCondition::increment);
        one.start();
        Thread two = new Thread(raceCondition::increment);
        two.start();
        one.join();
        two.join();
    }

    public synchronized void increment() {
        for (int i = 0; i < 99999; i++) {
            int current = number;
            int next = number + 1;
            if (current + 1 != next) {
                throw new IllegalStateException("Некорректное сравнение: " + current + " + 1 = " + next);
            }
        }
    }
}