package io.job4j.di;

import io.job4j.di.model.StartUI;
import io.job4j.di.model.Store;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ContextByListOfClasses {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Store.class, StartUI.class);
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.print();
    }
}