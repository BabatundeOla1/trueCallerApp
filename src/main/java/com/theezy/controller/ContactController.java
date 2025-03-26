package com.theezy.controller;

import com.theezy.data.models.Contact;
import com.theezy.dto.request.ContactRequest;
import com.theezy.dto.response.ContactResponse;
import com.theezy.services.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/contact")
@Service
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/saveContact")
    public ResponseEntity<ContactResponse> saveContact(@Valid @RequestBody ContactRequest contactRequest){
        return new ResponseEntity<>(contactService.saveContact(contactRequest), HttpStatus.OK);
    }

    @PostMapping("/deleteContact")
    public ContactResponse deleteContact(@Valid @RequestBody String phoneNumber){
        return  contactService.deleteOneContact(phoneNumber);
    }

    @GetMapping("/viewAllContact")
    public List<Contact> viewAllContacts() {
        return contactService.viewAllContacts();
    }
}
