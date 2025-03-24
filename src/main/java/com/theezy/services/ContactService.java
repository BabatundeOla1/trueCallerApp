package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;
import com.theezy.exceptions.ContactAlreadyExist;

public interface ContactService {
    ContactResponse saveContact(ContactRequest contactRequest) throws ContactAlreadyExist;
}
