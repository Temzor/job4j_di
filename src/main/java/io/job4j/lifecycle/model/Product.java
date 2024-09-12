package io.job4j.lifecycle.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Product implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private final String name;
    private final double price;
    private String beanName;

    public Product(String name, double price) {
        System.out.printf("1. вызван конструктор %s%n", getClass().getSimpleName());
        this.name = name;
        this.price = price;
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
        System.out.printf("2. этот бин зарегистрирован в контексте под именем '%s'%n", name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        boolean result = beanFactory.containsBean(beanName);
        System.out.printf("2. установка BeanFactory в бин '%s', Результат установки: '%s'%n", beanName, result);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.printf("2. выполняются действия setApplicationContext бина '%s'%n", beanName);
    }
    @PostConstruct
    public void annotInitMethod() {
        System.out.printf("4. @PostConstruct выполняются действия при инициализация бина '%s'%n", beanName);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.printf("4. InitializingBean: выполняются действия при инициализация бина '%s'%n", beanName);
    }

    public void initMethod() {
        System.out.printf("4. @Bean, свойство initMethod: выполняются действия при инициализация бина '%s'%n", beanName);
    }

    @PreDestroy
    public void oneMethodDestroy() {
        System.out.printf("6. @PreDestroy: выполняются действия при уничтожении бина '%s'%n", beanName);
    }

    @Override
    public void destroy() {
        System.out.printf("6. DisposableBean: выполняются действия при уничтожении бина '%s'%n", beanName);
    }

    public void destroyMethod() {
        System.out.printf("6. @Bean, свойство destroyMethod: - выполняются действия при уничтожении бина '%s'%n", beanName);
    }

    public String toString() {
        return String.format("%s: name=%s, price=$%.2f", getClass().getSimpleName(), name, price);
    }
}