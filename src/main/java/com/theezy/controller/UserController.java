package com.theezy.controller;

import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.request.UserLoginRequest;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.ContactResponse;
import com.theezy.dto.response.UserLoginResponse;
import com.theezy.dto.response.UserRegisterResponse;
import com.theezy.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        return new ResponseEntity<>(userService.registerUser(userRegisterRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> userLogin(@Valid @RequestBody UserLoginRequest userLoginRequest){
        return new ResponseEntity<>(userService.loginUser(userLoginRequest), HttpStatus.OK);
    }

    @PostMapping("/saveContact")
    public ResponseEntity<ContactResponse> saveContact(@Valid @RequestBody ContactRequest contactRequest){
        return new ResponseEntity<>(userService.saveContact(contactRequest), HttpStatus.OK);
    }

    @PostMapping("/deleteContact")
    public void deleteContact(@Valid @RequestBody String phoneNumber){
        userService.deleteContact(phoneNumber);
    }
}
