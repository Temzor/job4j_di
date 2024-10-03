package io.job4j.concurrent.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDiagnosis {
    static class Resource {}

    private static void acquireLocks(Resource r1, Resource r2) {
        synchronized (r1) {
            try { Thread.sleep(50); } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (r2) {}
        }
    }

    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        Thread thread1 = new Thread(() -> acquireLocks(resource1, resource2));
        Thread thread2 = new Thread(() -> acquireLocks(resource2, resource1));

        thread1.start();
        thread2.start();

        /* Запускаем диагностику в другом потоке, чтобы не блокировать main. */
        new Thread(DeadlockDiagnosis::diagnoseDeadlock).start();
    }

    private static void diagnoseDeadlock() {
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads();

        if (threadIds != null) {
            ThreadInfo[] infos = bean.getThreadInfo(threadIds);

            System.out.println("Дедлок обнаружен!");
            for (ThreadInfo info : infos) {
                System.out.println(info);
            }
        } else {
            System.out.println("Дедлоки не обнаружены.");
        }
    }
}
