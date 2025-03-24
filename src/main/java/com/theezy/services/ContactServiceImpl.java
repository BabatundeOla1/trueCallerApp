package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.data.repository.ContactRepository;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;
import com.theezy.exceptions.ContactAlreadyExist;
import com.theezy.utils.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactResponse saveContact(ContactRequest contactRequest) {
        if (checkIfContactExist(contactRequest.getPhoneNumber())){
            throw new ContactAlreadyExist("Contact Already exist.");
        }
        Contact newContact = ContactMapper.mapContactToRequest(contactRequest);
        contactRepository.save(newContact);
        return ContactMapper.mapContactToResponse(newContact);
    }

    private boolean checkIfContactExist(String phoneNumber){
        return contactRepository.existsByPhoneNumber(phoneNumber);
    }
}
