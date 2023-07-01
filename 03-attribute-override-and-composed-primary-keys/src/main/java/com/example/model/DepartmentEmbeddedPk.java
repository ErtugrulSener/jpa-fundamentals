package com.example.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class DepartmentEmbeddedPk implements Serializable {
    private Long id;
    private String code;
}
