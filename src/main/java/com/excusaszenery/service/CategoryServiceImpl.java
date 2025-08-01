package com.excusaszenery.service;

import com.excusaszenery.exception.ResourceNotFoundException;
import com.excusaszenery.model.Category;
import com.excusaszenery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excusaszenery.exception.ResourceConflictException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        if (categoryRepository.existsByCategoryName(category.getCategoryName())){
            throw new ResourceConflictException("La categoría ya existe.");
        } else {
            return categoryRepository.save(category);
        }
    }

    @Override
    public Category updateCategory(Integer id, Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        else {
            throw new ResourceNotFoundException("No existe categoría con ese id.");
            }
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
