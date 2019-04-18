package com.cart.catalogservice.resources.service;

import com.cart.catalogservice.resources.model.Category;
import com.cart.catalogservice.resources.model.Product;
import com.cart.catalogservice.resources.repository.CategoryRepository;
import com.cart.catalogservice.resources.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CatalogService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Product saveProduct(@NotNull Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> getProductById(@NotNull Long id) {
        return this.productRepository.findById(id);
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Product getProductByName(String name) {
        return this.productRepository.getProductByName(name);
    }
}
