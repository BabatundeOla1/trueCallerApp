package com.theezy.services;

import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.request.UserLoginRequest;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.ContactResponse;
import com.theezy.dto.response.UserLoginResponse;
import com.theezy.dto.response.UserRegisterResponse;

public interface UserService {

    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);
    ContactResponse saveContact(ContactRequest contactRequest);

    ContactResponse deleteContact(String phoneNumber);
}
