package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    ContactResponse saveContact(ContactRequest contactRequest);
    Contact searchContactByName(String name);
    ContactResponse deleteOneContact(String phoneNumber);
    ContactResponse editContact(ContactRequest contactRequest);
    List<Contact> viewAllContacts();
    Optional<Contact> searchContactByPhoneNumber(String phoneNumber);
}
