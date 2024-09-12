package io.job4j.lifecycle.config;


import io.job4j.lifecycle.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "myProduct",
            initMethod = "initMethod",
            destroyMethod = "destroyMethod"
    )
    public Product getProduct() {
        return new Product("Good Product", 2.5);
    }
}