package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.data.repository.ContactRepository;
import com.theezy.data.repository.UserRepository;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.utils.exceptions.UserAlreadyExistException;
import com.theezy.utils.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public void setUpUser(UserRegisterRequest userRegisterRequest){
        Contact contact = new Contact();
        contact.setEmail("Useremail@gmail.com");
        contact.setPhoneNumber("09036011444");
        contactRepository.save(contact);

        userRegisterRequest.setContact(contact);
        userRegisterRequest.setPassword("Babatunde123");
        userRegisterRequest.setFirstName("Babatunde");
        userRegisterRequest.setLastName("Olaleye");
    }
    @Test
    public void testThatUserCanRegister(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        setUpUser(userRegisterRequest);
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatErrorIsThrowWhenUserRegistersTwice(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        setUpUser(userRegisterRequest);
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepository.count());
        assertThrows(UserAlreadyExistException.class, ()-> userService.registerUser(userRegisterRequest));
    }

}