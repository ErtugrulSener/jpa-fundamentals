package com.example.relationships.entities;

import jakarta.persistence.Entity;

@Entity
public class Person extends Human {
    private String firstName;

    private String lastName;
}
