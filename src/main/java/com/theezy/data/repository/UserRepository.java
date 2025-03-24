package com.theezy.data.repository;

import com.theezy.data.models.Contact;
import com.theezy.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsUserByContact_PhoneNumber(String phoneNumber);
    boolean existsUserByContact_Email(String email);

    boolean existsUserById(String id);

}
