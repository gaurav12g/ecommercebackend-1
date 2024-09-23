package com.ecommerceBackend.ecommerceBackend.request;

import com.ecommerceBackend.ecommerceBackend.model.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateProductRequest {
    private String title;
    private String description;
    private int price;
    private  int discountedPrice;
    private int discountPersent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> size=new HashSet<>();
    private String imageUrl;
    private String topLavelCategory;
    private String secondLavelCategory;
    private String thirdLavelCategory;
}
