package com.example.relationships.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Product {
    /*
        - CRUD-Operations
        - Entity has to be in the context (that's why there is no @PreLoad)

        LOAD -- @PostLoad
        UPDATE -- @PreUpdate @PostUpdate
        REMOVE -- @PreRemove @PostRemove
        PERSIST -- @PrePersist @PostPersist
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        lastModified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastModified = LocalDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Entity " + this + " will be removed!");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("Entity " + this + " was removed!");
    }

    @PostLoad
    public void postLoad() {
        System.out.println("Entity " + this + " was loaded!");
    }
}
