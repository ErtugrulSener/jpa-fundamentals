package com.example.relationships;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

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

    /* SQL92
     * SQL - SELECT * FROM product p
     * JPQL - SELECT p FROM Product p
     * */

    @EventListener(ApplicationStartedEvent.class)
    public void applicationStarted() {
        var em = entityManagerFactory.createEntityManager();

        // persist
        em.getTransaction().begin();

        var products = List.of(new Product("Iphone 7", 1000.0), new Product("Iphone 8", 1150.0));
        products.forEach(em::persist);

        em.getTransaction().commit();

        em.getTransaction().begin();

        //        System.out.println(em.createQuery("SELECT p FROM Product p", Product.class).getResultList());

        //        System.out.println(em.createQuery("SELECT p FROM Product p WHERE p.price = :price", Product.class)
        //                .setParameter("price", 1000.0)
        //                .getResultList());

        //        System.out.println(em.createQuery("SELECT SUM(p.price) FROM Product p WHERE p.price = :price", Product.class)
        //                .setParameter("price", 1000.0)
        //                .getResultList());

        //        System.out.println(em.createQuery("SELECT p.id, p.price FROM Product p WHERE p.price = :price")
        //                .setParameter("price", 1000.0)
        //                .getResultList());

        // Use @NamedQuery on the Entity to specify it. The biggest advantage? The queries are checked on application startup!
        //        em.createNamedQuery()

        // Even though this one is possible... we should not use it, because when we do, we now depend on a specific technology
        // Use @NamedNativeQuery here if it's needed
        //        em.createNativeQuery()
        em.getTransaction().commit();

        em.close();
    }
}
