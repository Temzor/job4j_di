package io.job4j.annotation;

public class Dog implements Pet {
    private String name = "Dog";

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return "Dog name = %s".formatted(name);
    }
}