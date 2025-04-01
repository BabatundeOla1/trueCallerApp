package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    ContactResponse saveContact(ContactRequest contactRequest);
    Optional<Contact> searchContactByName(String name);
    ContactResponse deleteOneContact(String phoneNumber);

    String updateUserProfile(String userId, Contact contact);
    ContactResponse editContact(ContactRequest contactRequest);
    List<Contact> viewAllContacts();
    Optional<Contact> searchContactByPhoneNumber(String phoneNumber);
    Optional<Contact> blockContactByPhoneNumber(String phoneNumber);
    Optional<Contact> unblockContactByPhoneNumber(String phoneNumber);
    List<Contact> getBlockedContacts();
}
