package com.excusaszenery.service;

import com.excusaszenery.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Integer id, Category category);
    boolean deleteCategory(Integer id);
    Optional<Category> getCategoryById(Integer id);
    List<Category> getAllCategories();
}
