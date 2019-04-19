package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.Category;
import com.company.foxtask.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categories")
    public List<Category> allCategories() {
        return repository.findAll();
    }
}
