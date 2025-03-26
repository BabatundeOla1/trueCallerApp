package com.theezy.data.repository;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional<Contact> findContactByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Contact> findContactByName(String name);
    void deleteByPhoneNumber(String phoneNumber);
}
