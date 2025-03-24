package com.theezy.services;

import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.UserRegisterResponse;

public interface UserService {

    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
}
