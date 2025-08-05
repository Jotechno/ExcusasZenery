package com.excusaszenery.model;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "excuses")
@Getter
@Setter
public class Excuse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExcuse;

    @Column(nullable = false)
    @Size(min = 10, message = "El texto de la excusa debe tener mínimo 10 caracteres")
    @Size(max = 500, message = "El texto de la excusa no debe exceder 500 caracteres")
    @NotBlank(message = "El texto de la excusa es obligatorio")
    private String excuseText;

    private Integer likes = 0;
    private Integer dislikes = 0;

    private Boolean isPublic = true;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private  Category category;

    @Column(name = "creation_date", updatable = false, insertable = false)
    private LocalDate creationDate;

}
