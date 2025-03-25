package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;

import java.util.List;

public interface ContactService {
    ContactResponse saveContact(ContactRequest contactRequest);

    void deleteOneContact(String phoneNumber);
    ContactResponse editContact(ContactRequest contactRequest);
    List<Contact> viewAllContacts();
}
