package com.example.test1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tag {
    @Id
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }
}
