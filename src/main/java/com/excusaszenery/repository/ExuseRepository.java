package com.excusaszenery.repository;
import com.excusaszenery.model.Excuse;

import com.excusaszenery.model.User;
import jdk.jfr.Category;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExuseRepository extends JpaRepository<Excuse, Long>{
    Optional<Excuse> findById(Long id);
    List<Excuse> findByUsername(String username);
    List<Excuse> findByCategoryName(String categoryName);
}
