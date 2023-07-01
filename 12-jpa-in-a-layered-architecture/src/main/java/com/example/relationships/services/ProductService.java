package com.example.relationships.services;

import java.util.List;

import com.example.relationships.entities.Product;
import com.example.relationships.repositories.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductService {
    private final EntityManagerFactory entityManagerFactory;

    public ProductService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void addProduct(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        var productRepository = new ProductRepository(entityManager);

        Product product = new Product();
        product.setName(name);

        try {
            entityManager.getTransaction().begin();
            productRepository.addProduct(product);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public List<Product> findProducts() {
        var entityManager = entityManagerFactory.createEntityManager();
        var productRepository = new ProductRepository(entityManager);

        try {
            return productRepository.findAllProducts();
        } finally {
            entityManager.close();
        }
    }
}
