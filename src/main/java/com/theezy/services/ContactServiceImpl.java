package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.data.repository.ContactRepository;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;
import com.theezy.utils.exceptions.ContactAlreadyExist;
import com.theezy.utils.exceptions.ContactNotFoundException;
import com.theezy.utils.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactResponse saveContact(ContactRequest contactRequest)  {
        Optional<Contact> existingContact = contactRepository.findContactByPhoneNumber(contactRequest.getPhoneNumber());

        if (existingContact.isPresent()){
            throw new ContactAlreadyExist("Contact Already exist.");
        }
        Contact newContact = ContactMapper.mapContactToRequest(contactRequest);
        contactRepository.save(newContact);
        return ContactMapper.mapContactToResponse(newContact);
    }

    @Override
    public void deleteOneContact(String phoneNumber) {
        contactRepository.deleteByPhoneNumber(phoneNumber);
    }

    @Override
    public ContactResponse editContact(ContactRequest contactRequest) {
        Optional<Contact> existingContact = contactRepository.findContactByPhoneNumber(contactRequest.getPhoneNumber());
        if (existingContact.isPresent()){
            Contact updateContact = existingContact.get();
            updateContact.setName(contactRequest.getName());
            updateContact.setEmail(contactRequest.getEmail());
            updateContact.setAddress(contactRequest.getAddress());
            contactRepository.save(updateContact);
            return ContactMapper.mapContactToResponse(updateContact);
        }
        else{
            throw new ContactNotFoundException("Contact not found");
        }
    }

    @Override
    public List<Contact> viewAllContacts() {
        return contactRepository.findAll();
    }
}

