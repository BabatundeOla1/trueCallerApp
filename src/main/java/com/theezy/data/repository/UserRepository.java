package com.theezy.data.repository;

import com.theezy.data.models.Contact;
import com.theezy.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsUserByContact_PhoneNumber(String phoneNumber);
    Optional<User> findUserById(String userId);

    User findUserByContact_Email(String email);
    boolean existsUserById(String id);

}
