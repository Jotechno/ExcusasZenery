package com.excusaszenery.dto;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class UserRequestDto {
    //private String username;
    //private String email;
    //private String password;
    //private Boolean status;
    //private Integer roleId; // <-- este campo es necesario

   //Validaciones
   @NotBlank(message = "El nombre de usuario es obligatorio")
   @Size(min = 5, max = 50, message = "El nombre de usuario debe tener entre 5 y 50 caracteres")
   private String username;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    @Size(max = 100, message = "El correo no debe exceder 100 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, message = "La contraseña debe tener al menos 5 caracteres")
    private String password;

    private Boolean status = true;

    @NotNull(message = "Debe asignarse un rol al usuario")
    private Integer roleId;



    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getRoleId() { // <-- ESTE MÉTODO
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
