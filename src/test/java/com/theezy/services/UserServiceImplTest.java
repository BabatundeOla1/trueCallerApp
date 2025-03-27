package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.data.repository.ContactRepository;
import com.theezy.data.repository.UserRepository;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.request.UserLoginRequest;
import com.theezy.dto.request.UserRegisterRequest;
import com.theezy.dto.response.UserLoginResponse;
import com.theezy.utils.PasswordHashingService;
import com.theezy.utils.exceptions.UserAlreadyExistException;
import com.theezy.utils.exceptions.UserLoginInvalidException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void clearOTP_RepositoryBeforeEachTest(){
        userRepository.deleteAll();
    }
    @BeforeEach
    public void clearContactRepo(){
        contactRepository.deleteAll();
    }

    public void setUpUser(UserRegisterRequest userRegisterRequest){
        Contact contact = new Contact();
        contact.setEmail("Useremail@gmail.com");
        contact.setPhoneNumber("09036011444");
        contact.setName("You");
        contactRepository.save(contact);

        userRegisterRequest.setContact(contact);
        userRegisterRequest.setPassword("Babatunde123");
        userRegisterRequest.setFirstName("Babatunde");
        userRegisterRequest.setLastName("Olaleye");
    }

    public void setUserLogin(UserLoginRequest userLoginRequest){
        userLoginRequest.setEmail("Useremail@gmail.com");
        userLoginRequest.setPassword("Babatunde123");
    }

    public void setUpContact(ContactRequest contactRequest){
        contactRequest.setName("Earth");
        contactRequest.setEmail("Earth@gmail.com");
        contactRequest.setPhoneNumber("09063476087");
        contactRequest.setAddress("1, address street, lagos, yaba");
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
        Contact contact = new Contact();
        contact.setEmail("Babatunde@gmail.com");
        contact.setPhoneNumber("09012345687");
        contactRepository.save(contact);

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setContact(contact);
        userRegisterRequest.setPassword("Babatunde123");
        userRegisterRequest.setFirstName("Tunde");
        userRegisterRequest.setLastName("Olaleye");

        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepository.count());
        assertThrows(UserAlreadyExistException.class, ()-> userService.registerUser(userRegisterRequest));
    }
    @Test
    public void testThatUserCanLogin(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        setUpUser(userRegisterRequest);
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepository.count());

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        setUserLogin(userLoginRequest);
        UserLoginResponse loggedInUser = userService.loginUser(userLoginRequest);
        assertTrue(loggedInUser.isStatus());
    }

    @Test
    public void testThatErrorIsThrownWhenUserDetailsIsIncorrect(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        setUpUser(userRegisterRequest);
        userService.registerUser(userRegisterRequest);
        assertEquals(1, userRepository.count());

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("Useremail@gmail.com");
        userLoginRequest.setPassword("Babatunde1234");
        assertThrows(UserLoginInvalidException.class, ()-> userService.loginUser(userLoginRequest));
    }
}