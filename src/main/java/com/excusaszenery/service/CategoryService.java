package com.excusaszenery.service;

import com.excusaszenery.model.Category;
import com.excusaszenery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //Metodos importados directamente desde JPARepository
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findCategoryById(Long id){
        return categoryRepository.findById(id);
    }
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    //Metodos creados por mi para encontrar la categoria por nombre
    public Optional<Category> findCategoryByName(String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }
}
