package com.example.relationships.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DepartmentDetails {
    private String contractNo;

    @ManyToOne
    private Department department;
}
