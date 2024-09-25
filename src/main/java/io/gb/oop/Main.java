package io.gb.oop;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", "Blue", 3);
        Cat cat2 = new Cat("Murzik", "Yellow", 2);

        cat.info();
        cat2.info();
    }
}
