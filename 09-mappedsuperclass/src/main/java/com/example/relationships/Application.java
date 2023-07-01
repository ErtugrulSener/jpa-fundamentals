package com.example.relationships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.relationships.entities.Cat;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final EntityManager entityManager;

    @EventListener(ApplicationStartedEvent.class)
    @Transactional
    public void applicationStarted() {
        Cat cat = new Cat();
        cat.setColor("blue");
        cat.setName("George");
        entityManager.persist(cat);
    }
}
