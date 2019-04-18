package com.cart.catalogservice;

import com.cart.catalogservice.resources.model.Category;
import com.cart.catalogservice.resources.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ThecartCatalogServiceApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public ThecartCatalogServiceApplication(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ThecartCatalogServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category();
        category1.setName("Category1");
        category1.setDescription("Description1");
        category1.setCreated(new Date());
        category1.setLastUpdated(new Date());
        this.categoryRepository.save(category1);
    }
}
