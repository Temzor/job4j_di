package io.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import io.job4j.di.model.StartUI;

public class ContextByJavaBasedConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("io.job4j.di.model");
        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
    }
}