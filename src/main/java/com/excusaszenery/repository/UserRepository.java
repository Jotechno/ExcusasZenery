package com.excusaszenery.repository;

import com.excusaszenery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);         // útil para login
    User findByUsername(String username);   // útil para recuperar perfil
    List<User> findByStatus(Boolean status); // para listar usuarios activos/inactivos
}
