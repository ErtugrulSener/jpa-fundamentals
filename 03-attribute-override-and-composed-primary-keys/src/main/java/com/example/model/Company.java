package com.example.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Company {
    private String fullName;

    private String companyAddress;
}
