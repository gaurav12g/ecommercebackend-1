package com.ecommerceBackend.ecommerceBackend.controller;

import com.ecommerceBackend.ecommerceBackend.exception.UserException;
import com.ecommerceBackend.ecommerceBackend.model.User;
import com.ecommerceBackend.ecommerceBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User>getUserProfileHandler(@RequestHeader("Authorization")String jwt)throws UserException{
        User user= userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

}
