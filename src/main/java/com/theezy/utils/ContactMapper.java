package com.theezy.utils;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;

public class ContactMapper {
    public static Contact mapContactToRequest(ContactRequest contactRequest){
        Contact contact = new Contact();
        contact.setName(contactRequest.getName());
        contact.setEmail(contactRequest.getEmail());
        contact.setAddress(contactRequest.getAddress());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        contact.setId(contactRequest.getId());
        return contact;
    }

    public static ContactResponse mapContactToResponse(Contact contact){
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setMessage("Successful");
        contactResponse.setData(contact.getId());
        return contactResponse;
    }
}
