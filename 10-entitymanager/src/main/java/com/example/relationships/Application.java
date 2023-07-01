package com.example.relationships;

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

    /*
     * persist()
     *  - is like merge, but will create a new instance if it isn't inside the context
     *
     * merge()
     *  - is like persist but excepts the entity to be in the context (and managed) already
     *
     * flush()
     *
     * find()
     *  - will return null if nothing is found
     *  - will trigger a SELECT query immediately
     *
     * getReference()
     *  - throws an EntityNotFoundException if the reference is not found#
     *  - will not cause a SELECT query until the referenced entity (variable) is used
     *
     * contains()
     *  - Search if the entity is inside the current context
     *
     * detach()
     *  - Remove an instance from the current context
     *
     * clear()
     *  - Remove all entities from the current context
     *
     * remove()
     *  - Will remove the entity from the current context + DELETE from database on commit / flush
     *
     * refresh()
     *  - Undo all changes that has been done to the current entity inside of the context
     * */

    @EventListener(ApplicationStartedEvent.class)
    public void applicationStarted() {
        var em = entityManagerFactory.createEntityManager();

        // persist
        em.getTransaction().begin();

        Product product = new Product();
        product.setName("Ertu");

        em.persist(product);
        em.getTransaction().commit();

        // find
        // em.getTransaction().begin();
        //
        // Product p = em.find(Product.class, product.getId());
        // System.out.println(p.getName());
        // em.persist(p);
        // em.getTransaction().commit();

        em.getTransaction().begin();
        Product p = em.getReference(Product.class, 1L);
        System.out.println(p.getName());

        em.getTransaction().commit();

        em.close();
    }
}
