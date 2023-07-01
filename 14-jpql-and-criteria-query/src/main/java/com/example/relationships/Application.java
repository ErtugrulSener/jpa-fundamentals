package com.example.relationships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import com.example.relationships.entities.Product;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.Root;
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

        int productCount = 5;

        while (--productCount > 0) {
            var product = new Product();
            product.setName("IIphone 5");

            entityManager.persist(product);
        }

        entityManager.getTransaction().commit();
    }

    // @EventListener(ApplicationStartedEvent.class)
    @Order(2)
    public void exampleWithJpql() {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String jpql = """
                SELECT p FROM Product p
                """;
        entityManager.createQuery(jpql, Product.class).getResultList().forEach(System.out::println);

        entityManager.getTransaction().commit();
    }

    //    @EventListener(ApplicationStartedEvent.class)
    @Order(3)
    public void exampleWithCriteriaQuery() {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Product.class);
        var typedQuery = criteriaQuery.select(criteriaQuery.from(Product.class));

        entityManager.createQuery(typedQuery).getResultList().forEach(System.out::println);

        entityManager.getTransaction().commit();
    }

    @EventListener(ApplicationStartedEvent.class)
    @Order(4)
    public void commonMistakeWithCriteriaQuery() {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // The calls on criteria query change the query
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        // This will cause multiple froms in the query (so causing a cross join on the table with itself)
        var typedQuery = criteriaQuery.select(criteriaQuery.from(Product.class));

        entityManager.createQuery(typedQuery).getResultList().forEach(System.out::println);

        entityManager.getTransaction().commit();
    }
}
