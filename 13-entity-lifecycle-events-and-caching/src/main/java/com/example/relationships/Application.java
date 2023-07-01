package com.example.relationships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import com.example.relationships.entities.Product;

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
    @Order(1)
    public void applicationStarted() {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var product = new Product();
        product.setName("IIphone 5");

        entityManager.persist(product);

        entityManager.getTransaction().commit();
    }

    @EventListener(ApplicationStartedEvent.class)
    @Order(2)
    public void applicationStarted2() {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var product = entityManager.find(Product.class, 1L);
        product.setName("IIphone 6");

        entityManager.merge(product);

        entityManager.getTransaction().commit();
    }
}
