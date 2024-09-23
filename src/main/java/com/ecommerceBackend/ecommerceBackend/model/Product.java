package com.ecommerceBackend.ecommerceBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private  String description;

    @Column(name="price")
    private int price;

    @Column(name = "discount_present")
    private int discountPresent;

    @Column(name = "discounted_price")
    private int discountedPrice;

    @Column(name = "discount_persent")
    private int discountPersent;

    @Column(name = "quantity")
    private int quantity;

    private String brand;
    private String color;

    @Embedded
    @ElementCollection
    @Column(name="sizes")
    private Set<Size>sizes=new HashSet<>();

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Rating>rating =new ArrayList<>();

    @Column(name = "num_rating")
    private int numRating;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private LocalDateTime  createdAt;


}
