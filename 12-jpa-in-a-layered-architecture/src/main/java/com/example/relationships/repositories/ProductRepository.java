package com.example.relationships.repositories;

import java.util.List;

import com.example.relationships.entities.Product;

import jakarta.persistence.EntityManager;

public class ProductRepository {
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    public List<Product> findAllProducts() {
        String query = """
                SELECT p FROM Product p
                """;
        return entityManager.createQuery(query, Product.class).getResultList();
    }
}
