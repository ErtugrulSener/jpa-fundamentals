package com.example.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Access(AccessType.PROPERTY)
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private Company company;

    @Embedded
    // Not really needed for spring boot applications with ddl-auto set, since the database columns are created for us
    // @AttributeOverrides({ @AttributeOverride(name = "cn", column = @Column(name = "company_name")) })
    @AttributeOverride(name = "fullName", column = @Column(name = "company_full_name"))
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
