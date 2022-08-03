package com.faisal.productsandcategories.services;

import com.faisal.productsandcategories.models.Category;
import com.faisal.productsandcategories.models.Product;
import com.faisal.productsandcategories.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public void createProduct(Product p){
        productRepository.save(p);
    }

    public Product findProduct(long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    public void addCategoryToProduct(Product product,Category category){
        productRepository.save(product);
    }
}
