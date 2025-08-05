package com.excusaszenery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(length = 50, nullable = false)
    @Size(max = 50, message = "El nombre de usuario no debe exceder 50 caracteres")
    @Size(min = 5, message = "El nombre de usuario debe tener al menos 5 caracteres")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @Column(length = 100, nullable = false)
    @Email(message = "Debe ser un correo válido")
    @Size(max = 100, message = "El correo no debe exceder 100 caracteres")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @Column(length = 60, nullable = false)
    @Size(min = 5, message = "La contraseña debe tener al menos 5 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Excuse> excuses;

}
