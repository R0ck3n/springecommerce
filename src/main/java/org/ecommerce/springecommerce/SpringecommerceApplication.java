package org.ecommerce.springecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import java.time.LocalDate;

@ComponentScan("org.ecommerce")
@SpringBootApplication
public class SpringecommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringecommerceApplication.class, args);
    }



}
