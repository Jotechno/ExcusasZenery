package com.excusaszenery.controller;

import com.excusaszenery.model.Category;
import com.excusaszenery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean deleted = categoryService.deleteCategory(id);
        return deleted ? ResponseEntity.ok("Categoria borrada") : ResponseEntity.badRequest().body("Categoria no encontrada");
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Integer id) {
        return categoryService.getCategoryById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
