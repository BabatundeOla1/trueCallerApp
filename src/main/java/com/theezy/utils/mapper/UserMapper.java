package com.theezy.utils.mapper;

import com.theezy.data.models.User;
import com.theezy.dto.request.UserLoginRequest;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.UserLoginResponse;
import com.theezy.dto.response.UserRegisterResponse;

public class UserMapper {

    public static User mapRequestToUser(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setContact(userRegisterRequest.getContact());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setPassword(userRegisterRequest.getPassword());
        return user;
    }

    public static UserRegisterResponse mapUserToResponse(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage("Successful");
        userRegisterResponse.setData(user.getId());
        return userRegisterResponse;
    }

    public static UserLoginResponse mapUserToLoginResponse(String message){
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setMessage(message);
        userLoginResponse.setStatus(true);
        return userLoginResponse;
    }
}
