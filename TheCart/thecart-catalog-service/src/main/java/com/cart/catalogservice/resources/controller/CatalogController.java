package com.cart.catalogservice.resources.controller;

import com.cart.catalogservice.resources.exceptions.ResourceNotFoundException;
import com.cart.catalogservice.resources.model.Product;
import com.cart.catalogservice.resources.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@Valid @PathVariable Long id) {
        Optional<Product> productById = this.catalogService.getProductById(id);
        if (!productById.isPresent()) throw new ResourceNotFoundException("Product with ID:" + id + " NOT FOUND");
        return new ResponseEntity<>(productById.get(), HttpStatus.FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody @NotNull Product product) {
        product.setCreated(new Date());
        product.setLastUpdated(new Date());
        product.setCategory(product.getCategory());
        this.catalogService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
