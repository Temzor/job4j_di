package io.job4j.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan(basePackages = "io.job4j.annotation")
@PropertySource("classpath:application.properties")
public class Config {

    @Bean
    @Primary
    @Order(5)
    public Dog getAlfaDog() {
        return new Dog("AlfaDog");
    }

    @Bean(name = "SecondDog")
    @Order(11)
    public Dog getDog() {
        return new Dog("Dog");
    }
    @Bean(name = "SomeDog")
    @Order(15)
    public Dog someDog() {
        return new Dog("SomeDog");
    }

}