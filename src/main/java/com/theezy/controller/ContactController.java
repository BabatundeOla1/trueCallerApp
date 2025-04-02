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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/contact")
@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/saveContact")
    public ResponseEntity<ContactResponse> saveContact(@Valid @RequestBody ContactRequest contactRequest){
        return new ResponseEntity<>(contactService.saveContact(contactRequest), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteContact/{phoneNumber}")
    public ResponseEntity<ContactResponse> deleteContact(@PathVariable("phoneNumber") String phoneNumber) {
        ContactResponse response = contactService.deleteOneContact(phoneNumber);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/editContact")
    public ContactResponse editContact(@Valid @RequestBody ContactRequest contactRequest){
        return contactService.editContact(contactRequest);
    }

    @PostMapping("/update-userProfile/{userId}")
    public ResponseEntity<Map<String, String>> updateUserProfile(@PathVariable("userId") String userId, @RequestBody Contact contact) {
        String message = contactService.updateUserProfile(userId, contact);

        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewAllContact")
    public List<Contact> viewAllContacts() {
        return contactService.viewAllContacts();
    }

    @GetMapping("/search/{number}")
    public Optional<Contact> searchContactByPhoneNumber(@Valid @PathVariable("number") String phoneNumber){
        return contactService.searchContactByPhoneNumber(phoneNumber);
    }

    @GetMapping("/searchByName/{name}")
    public Optional<Contact> searchContactByName(@Valid @PathVariable("name")  String name){
        return contactService.searchContactByName(name);
    }

    @PatchMapping("/block/{number}")
    public Optional<Contact> blockContact(@PathVariable("number") String phoneNumber) {
        return contactService.blockContactByPhoneNumber(phoneNumber);
    }

    @PatchMapping("/unblock/{number}")
    public ResponseEntity<Contact> unblockContact(@PathVariable("number") String phoneNumber) {
        Optional<Contact> unblockedContact = contactService.unblockContactByPhoneNumber(phoneNumber);
        return unblockedContact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/contacts/viewBlocked")
    public List<Contact>   getBlockedContacts() {
        return contactService.getBlockedContacts();
    }
}
