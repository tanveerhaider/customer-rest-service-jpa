package com.simplejava;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer API", version = "1.0", description = "Rest service to interact with customer resource"))
public class CustomerServiceJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceJpaApplication.class, args);
    }

}
