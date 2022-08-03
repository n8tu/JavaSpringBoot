package com.faisal.productsandcategories.services;

import com.faisal.productsandcategories.models.Category;
import com.faisal.productsandcategories.models.Product;
import com.faisal.productsandcategories.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> allCategories(){
        return categoryRepository.findAll();
    }

    public void createCategory(Category c){
        categoryRepository.save(c);
    }

    public Category findCategory(long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }
}
