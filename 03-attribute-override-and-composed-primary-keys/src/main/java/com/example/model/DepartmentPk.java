package com.example.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DepartmentPk implements Serializable {
    private Long id;
    private String code;
}
