package com.cart.catalogservice;

import com.cart.catalogservice.resources.model.Image;
import com.cart.catalogservice.resources.model.Product;
import com.cart.catalogservice.resources.service.CatalogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogRepositoryTest {

    @Autowired
    private CatalogService catalogService;


    @Transactional
    @Test
    public void testProductSave() {
        Product p1 = createProduct("p1", "pd", 10.00, 10, "thumbnail", "image", new Date(), new Date(),catalogService.getAllCategories().get(0).getName());
        Product p2 = createProduct("p2", "pd", 10.00, 10, "thumbnail", "image", new Date(), new Date(),catalogService.getAllCategories().get(0).getName());
        Product p3 = createProduct("p3", "pd", 10.00, 10, "thumbnail", "image", new Date(), new Date(),catalogService.getAllCategories().get(0).getName());
        this.catalogService.saveProduct(p1);
        this.catalogService.saveProduct(p2);
        this.catalogService.saveProduct(p3);

        Product p1db = catalogService.getProductByName("p1");
        Product p2db = catalogService.getProductByName("p2");
        Product p3db = catalogService.getProductByName("p3");

        Assert.assertNotNull(p1db);
        Assert.assertNotNull(p2db);
        Assert.assertNotNull(p3db);

        Assert.assertTrue(p1db.getName().equals("p1"));
        Assert.assertTrue(p2db.getName().equals("p2"));
        Assert.assertTrue(p3db.getName().equals("p3"));


    }

    protected Product createProduct(String productName, String productDescription, Double price,
                                    int quantity, String thumbnail,
                                  String imgLoc, Date created, Date lastUpdated, String category) {

        List<Image> images = new ArrayList<>();
        Image image1 = new Image();
        image1.setImageLocation(imgLoc);
        image1.setImageType(thumbnail);

        images.add(image1);
        Product product = new Product();
        product.setName(productName);
        product.setDescription(productDescription);
        product.setPrice(price);
        product.setAvailableQuantity(quantity);
        product.setImages(images);
        product.setCreated(created);
        product.setLastUpdated(lastUpdated);
        product.setCategory(category);
        image1.setProduct(product);
        return product;
    }
}
