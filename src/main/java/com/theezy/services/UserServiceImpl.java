package com.theezy.services;

import com.theezy.data.models.User;
import com.theezy.data.repository.UserRepository;
import com.theezy.dto.request.UserLoginRequest;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.UserLoginResponse;
import com.theezy.dto.response.UserRegisterResponse;
import com.theezy.utils.PasswordHashingService;
import com.theezy.utils.exceptions.UserAlreadyExistException;
import com.theezy.utils.exceptions.UserLoginInvalidException;
import com.theezy.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactService contactService;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        boolean isUserExist = checkIfUserExist(userRegisterRequest.getContact().getPhoneNumber()) ||
                checkIfUserExistByEmail(userRegisterRequest.getContact().getEmail());
        if (isUserExist){
            throw new UserAlreadyExistException("User already exist");
        }

        String hashedPassword = PasswordHashingService.hashPassword(userRegisterRequest.getPassword());

        User user = new User();
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setContact(userRegisterRequest.getContact());
        user.getContact().setBlocked(false);
        userRegisterRequest.getContact().setName("You");
        user.setPassword(hashedPassword);

        userRepository.save(user);
        return UserMapper.mapUserToResponse(user);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        User foundUser = userRepository.findUserByContact_Email(userLoginRequest.getEmail());
        boolean isPasswordValid = PasswordHashingService.checkPassword(userLoginRequest.getPassword(), foundUser.getPassword());

        if (!isPasswordValid){
            throw new UserLoginInvalidException("Invalid password");
        }
        return UserMapper.mapUserToLoginResponse("successful");
    }

    private boolean checkIfUserExist(String phoneNumber){
        return userRepository.existsUserByContact_PhoneNumber(phoneNumber);
    }
    private boolean checkIfUserExistByEmail(String email){
        return userRepository.existsUserByContact_PhoneNumber(email);
    }

}
