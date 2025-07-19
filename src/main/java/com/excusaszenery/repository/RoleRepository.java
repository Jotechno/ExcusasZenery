package com.excusaszenery.repository;

import com.excusaszenery.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
    Optional<Role> findById(Long id);
}
