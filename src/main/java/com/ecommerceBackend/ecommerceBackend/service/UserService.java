package com.ecommerceBackend.ecommerceBackend.service;


import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.User;

public interface UserService {
    public User findUserById(Long userId)throws UserException;
    public User findUserProfileByJwt(String jwt)throws UserException;
}
