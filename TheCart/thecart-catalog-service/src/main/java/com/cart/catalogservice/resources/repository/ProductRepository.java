package com.cart.catalogservice.resources.repository;

import com.cart.catalogservice.resources.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p  WHERE p.name =  ?1")
    Product getProductByName(String name);
}
