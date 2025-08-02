package com.excusaszenery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter

public class Category {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Excuse> excuses;

}
