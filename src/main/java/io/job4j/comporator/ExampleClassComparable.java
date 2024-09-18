package io.job4j.comporator;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ExampleClassComparable {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(UUID.randomUUID(), "Dmitrii", "Durov", 60000));
        employees.add(new Employee(UUID.randomUUID(), "Ivan", "Alco", 80000));
        employees.add(new Employee(UUID.randomUUID(), "Ivan", "Mex", 70000));
        employees.add(new Employee(UUID.randomUUID(), "Ann", "Liker", 20000));

        System.out.println("Before sorting:");
        System.out.println(employees);
        Collections.sort(employees);
        System.out.println("After sorting:");
        System.out.println(employees);
    }

}


@AllArgsConstructor
@ToString
class Employee implements Comparable<Employee> {
    UUID id;
    String name;
    String surname;
    int salary;

    @Override
    public int compareTo(Employee o) {
        /*
        * Сравнение через comparable IntegerInteger.compare(this.salary, o.salary);
        * Сравнение через тернарный оператор, чтобы возвращались числа -1; 0 ; 1 this.salary > o.salary
        ? 1 : this.salary == o.salary ? 0 : -1;
        * Сравнение через метод compareTo интерфейса Comparable this.name.compareTo(o.name), compareTo -
        использовать необходимо только для ссылочных типов данных, бесполезно для примитивных;
        * Интерфейс Comparable используется для сравнения объектов, используя естественный порядок (natural order).
        */
        var result = this.name.compareTo(o.name);
        return (result == 0) ? this.surname.compareTo(o.surname) : result;

    }
}