package io.job4j.synch;

import io.job4j.concurrent.synch.SingleLockList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleLockListTest {

    @Test
    public void whenIt() {
        var init = new ArrayList<Integer>();
        SingleLockList<Integer> list = new SingleLockList<>(init);
        list.add(1);
        var it = list.iterator();
        list.add(2);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenAdd() throws InterruptedException {
        var init = new ArrayList<Integer>();
        SingleLockList<Integer> list = new SingleLockList<>(init);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl).hasSize(2).containsAll(Set.of(1, 2));
    }

    @Test
    public void whenAddThenGet() {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        list.add(1);
        Integer result = list.get(0);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void whenAddInMultipleThreadsThenAllElementsAdded() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread thread1 = new Thread(() -> list.add(1));
        Thread thread2 = new Thread(() -> list.add(2));
        Thread thread3 = new Thread(() -> list.add(3));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(list).hasSize(3).containsAll(Set.of(2));
        assertThat(list).containsExactlyInAnyOrder(1, 2, 3);

    }

    @Test
    public void whenIterateThenNoConcurrentModification() throws InterruptedException {
        List<Integer> initial = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            initial.add(i);
        }
        SingleLockList<Integer> list = new SingleLockList<>(initial);

        Thread iteratorThread = new Thread(() -> {
            for (Integer i : list) {
                System.out.println(i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread modifyingThread = new Thread(() -> list.add(5));

        iteratorThread.start();
        modifyingThread.start();

        iteratorThread.join();
        modifyingThread.join();

        assertThat(list).containsExactly(0, 1, 2, 3, 4, 5);
    }
}