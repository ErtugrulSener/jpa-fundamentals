package com.example.relationships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import com.example.relationships.services.ProductService;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final EntityManagerFactory entityManagerFactory;

    @EventListener(ApplicationStartedEvent.class)
    public void applicationStarted() {
        ProductService service = new ProductService(entityManagerFactory);
        service.addProduct("Beer");
        service.addProduct("Chocolate");
        service.findProducts().forEach(System.out::println);
    }
}
