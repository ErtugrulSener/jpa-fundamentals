package com.example.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DepartmentWithEmbeddedId {
    @EmbeddedId
    private DepartmentEmbeddedPk id;
}
