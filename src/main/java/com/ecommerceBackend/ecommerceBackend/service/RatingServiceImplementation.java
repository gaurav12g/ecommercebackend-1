package com.ecommerceBackend.ecommerceBackend.service;

import com.ecommerceBackend.ecommerceBackend.exception.ProductException;
import com.ecommerceBackend.ecommerceBackend.model.Product;
import com.ecommerceBackend.ecommerceBackend.model.Rating;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.repository.RatingRepository;
import com.ecommerceBackend.ecommerceBackend.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{
    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService){
        this.productService=productService;
        this.ratingRepository=ratingRepository;
    }
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());
        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
