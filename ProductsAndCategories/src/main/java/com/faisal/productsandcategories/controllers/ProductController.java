package com.faisal.productsandcategories.controllers;

import com.faisal.productsandcategories.models.Category;
import com.faisal.productsandcategories.models.Product;
import com.faisal.productsandcategories.services.CategoryService;
import com.faisal.productsandcategories.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/products" , method = RequestMethod.GET)
    public String allProducts(
            Model model
    ){
        model.addAttribute("products", productService.allProducts());
        return "products_dashboard.jsp";
    }

    @RequestMapping(value = "/products/new" , method = RequestMethod.GET)
    public String newProduct(
            @ModelAttribute("product") Product product
            ){
        return "new_product.jsp";
    }

    @RequestMapping(value = "/products/new" , method = RequestMethod.POST)
    public String processNewProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result
    ){
        if(result.hasErrors()){
            return "redirect:/products/new";
        }
        productService.createProduct(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/{product_id}", method = RequestMethod.GET)
    public String showProduct(
            @PathVariable(value = "product_id") long product_id,
            @ModelAttribute("category") Category category,
            Model model
    ){
        model.addAttribute("categories",categoryService.allCategories());
        model.addAttribute("product", productService.findProduct(product_id));
        return "show_product.jsp";
    }

    @RequestMapping(value = "/products/{product_id}/add_category",method = RequestMethod.POST)
    public String addCategoryToProduct(
            @PathVariable(value = "product_id") long product_id,
            @RequestParam(value = "category_id") long category_id


    ){
        Product product = productService.findProduct(product_id);
        Category category = categoryService.findCategory(category_id);
        productService.addCategoryToProduct(product,category);
        return "redirect:/products/" + product_id;
    }


}
