package com.excusaszenery.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter

public class Category {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(nullable = false)
    private String categoryName;
}
