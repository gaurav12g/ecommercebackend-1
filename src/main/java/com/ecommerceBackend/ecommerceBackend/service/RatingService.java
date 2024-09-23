package com.ecommerceBackend.ecommerceBackend.service;

import com.ecommerceBackend.ecommerceBackend.exception.ProductException;
import com.ecommerceBackend.ecommerceBackend.model.Rating;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest req, User user)throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}
