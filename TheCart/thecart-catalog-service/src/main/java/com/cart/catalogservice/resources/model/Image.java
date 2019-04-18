package com.cart.catalogservice.resources.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String imageType;
    @Column(nullable = false)
    private String imageLocation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

}
