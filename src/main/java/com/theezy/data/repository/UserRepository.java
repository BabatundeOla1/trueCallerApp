package com.theezy.data.repository;

import com.theezy.data.models.Contact;
import com.theezy.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
