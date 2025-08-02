package com.excusaszenery.repository;

import com.excusaszenery.model.Excuse;
import com.excusaszenery.model.User;
import com.excusaszenery.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcuseRepository extends JpaRepository<Excuse, Integer> {
    List<Excuse> findByUser(User user);

    List<Excuse> findByCategory(Category category);

    List<Excuse> findByIsPublicTrue();
    List<Excuse> findByIsPublicFalse();

    List<Excuse> findByExcuseTextContainingIgnoreCase(String text);
}
