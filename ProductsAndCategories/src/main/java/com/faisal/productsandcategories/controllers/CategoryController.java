package com.faisal.productsandcategories.controllers;

import com.faisal.productsandcategories.models.Category;
import com.faisal.productsandcategories.models.Product;
import com.faisal.productsandcategories.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/categories" , method = RequestMethod.GET)
    public String allCategories(
            Model model
    ){
        model.addAttribute("categories", categoryService.allCategories());
        return "categories_dashboard.jsp";
    }

    @RequestMapping(value = "/categories/new" , method = RequestMethod.GET)
    public String newCategory(
            @ModelAttribute("category") Category category
    ){
        return "new_category.jsp";
    }

    @RequestMapping(value = "/categories/new" , method = RequestMethod.POST)
    public String processNewCategory(
            @Valid @ModelAttribute("category") Category category,
            BindingResult result
    ){
        if(result.hasErrors()){
            return "redirect:/categories/new";
        }
        categoryService.createCategory(category);
        return "redirect:/categories";
    }


}
