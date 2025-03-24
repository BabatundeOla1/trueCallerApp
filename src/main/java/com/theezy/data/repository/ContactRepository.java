package com.theezy.data.repository;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {
    ContactRequest findContactByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
    Contact findContactByName(String name);
}
