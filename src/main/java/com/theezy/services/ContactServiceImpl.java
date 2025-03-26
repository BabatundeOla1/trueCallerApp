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
    public Optional<Contact> searchContactByName(String name) {
        return contactRepository.findContactByName(name);
    }

    @Override
    public ContactResponse deleteOneContact(String phoneNumber) {
        contactRepository.deleteByPhoneNumber(phoneNumber);
        return ContactMapper.mapToDeleteContact("Successfully deleted.");

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

    @Override
    public Optional<Contact> searchContactByPhoneNumber(String phoneNumber) {
        return  contactRepository.findContactByPhoneNumber(phoneNumber)
                .filter(contact -> !contact.isBlocked());
    }

    @Override
    public Optional<Contact> blockContactByPhoneNumber(String phoneNumber) {
        Optional<Contact> foundContact = contactRepository.findContactByPhoneNumber(phoneNumber);
        if (foundContact.isPresent()){
            Contact contactToBlock = foundContact.get();
            contactToBlock.setBlocked(true);
            contactRepository.save(contactToBlock);
            return Optional.of(contactToBlock);
        }
        return Optional.empty();
    }
    @Override
    public Optional<Contact> unblockContactByPhoneNumber(String phoneNumber) {
        Optional<Contact> foundContact = contactRepository.findContactByPhoneNumber(phoneNumber);
        if (foundContact.isPresent()){
            Contact contactToUnblock = foundContact.get();
            contactToUnblock.setBlocked(false);
            contactRepository.save(contactToUnblock);
            return Optional.of(contactToUnblock);
        }
        return Optional.empty();
    }

    @Override
    public List<Contact> getBlockedContacts() {
        return contactRepository.findAllByBlockedIsTrue();
    }
}

