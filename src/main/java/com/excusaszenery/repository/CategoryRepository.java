package com.excusaszenery.repository;
import com.excusaszenery.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
    Optional<Category> findByCategoryName(String categoryName);
}
