package com.theezy.services;

import com.theezy.data.models.User;
import com.theezy.data.repository.UserRepository;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.UserRegisterResponse;
import com.theezy.utils.PasswordHashingService;
import com.theezy.utils.exceptions.UserAlreadyExistException;
import com.theezy.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        if (checkIfUserExist(userRegisterRequest.getContact().getPhoneNumber())){
            throw new UserAlreadyExistException("User already exist");
        }

        String hashedPassword = PasswordHashingService.hashPassword(userRegisterRequest.getPassword());

        User user = new User();
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setContact(userRegisterRequest.getContact());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        return UserMapper.mapUserToResponse(user);
    }
    private boolean checkIfUserExist(String phoneNumber){
        return userRepository.existsUserByContact_PhoneNumber(phoneNumber);
    }

}
