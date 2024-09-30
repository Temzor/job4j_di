package io.job4j.concurrent.phaser;

import java.util.concurrent.Phaser;

public class Education {

    public static void main(String[] args) {
        /* Создаем Phaser без участников */
        final Phaser phaser = new Phaser();

        /* Модель студентов */
        class Student implements Runnable {
            private final String name;
            private final Phaser phaser;

            public Student(String name, Phaser phaser) {
                this.name = name;
                this.phaser = phaser;
                this.phaser.register(); /* Регистрируем участника */
            }

            @Override
            public void run() {
                System.out.println(name + " прибыл на обучение.");
                /* Модуль 1 */
                study("Модуль 1");
                phaser.arriveAndAwaitAdvance(); /* Ожидаем других */

                /* Модель: студент решает покинуть обучение после модуля 1 */
                if ("Студент 3".equals(name)) {
                    System.out.println(name + " решил прекратить обучение.");
                    phaser.arriveAndDeregister(); /* Прибыл и снимается с регистрации */
                    return;
                }

                /* Модуль 2 */
                study("Модуль 2");
                phaser.arriveAndAwaitAdvance();

                /* Новый студент присоединяется перед модулем 3 */
                if ("Студент 2".equals(name)) {
                    System.out.println("Новый студент присоединяется перед модулем 3.");
                    new Thread(new Student("Студент 4", phaser)).start();
                }

                /* Модуль 3 */
                study("Модуль 3");

                phaser.arriveAndAwaitAdvance();

                phaser.arriveAndDeregister(); /* Завершаем участие */
                System.out.println(name + " завершил обучение.");
            }

            private void study(String module) {
                System.out.println(name + " изучает " + module);
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " завершил " + module);
            }
        }

        /* Создаем студентов */
        new Thread(new Student("Студент 1", phaser)).start();
        new Thread(new Student("Студент 2", phaser)).start();
        new Thread(new Student("Студент 3", phaser)).start();
    }
}