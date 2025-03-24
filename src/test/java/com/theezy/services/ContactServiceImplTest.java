package com.theezy.services;

import com.theezy.data.models.Contact;
import com.theezy.data.repository.ContactRepository;
import com.theezy.dto.request.ContactRequest;
import com.theezy.utils.exceptions.ContactAlreadyExist;
import com.theezy.utils.exceptions.ContactNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceImplTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void clearContactRepo(){
        contactRepository.deleteAll();
    }
    public void setUpContact(ContactRequest contactRequest){
        contactRequest.setName("Babatunde Olaleye");
        contactRequest.setEmail("emailAddress@gmail.com");
        contactRequest.setPhoneNumber("09012345678");
        contactRequest.setAddress("1, address street, lagos, yaba");
    }

    @Test
    public void testThatContactRepositoryIsEmpty(){
        assertEquals(0, contactRepository.count());
    }
    @Test
    public void testThatContactCanBeSaved(){
        ContactRequest contactRequest = new ContactRequest();
        setUpContact(contactRequest);
        contactService.saveContact(contactRequest);
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void testThatContactCanBeDeletedFromRepository(){
        ContactRequest contactRequest = new ContactRequest();
        setUpContact(contactRequest);
        contactService.saveContact(contactRequest);

        ContactRequest newContactRequest = new ContactRequest();
        newContactRequest.setName("Theezy Olaleye");
        newContactRequest.setEmail("Theezy@gmail.com");
        newContactRequest.setPhoneNumber("09036011444");
        newContactRequest.setAddress("2, address street, lagos, yaba");
        contactService.saveContact(newContactRequest);

        assertEquals(2, contactRepository.count());

        contactService.deleteOneContact(newContactRequest.getPhoneNumber());
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void testThatExceptionIsThrownWhenContactAlreadyExist(){
        ContactRequest contactRequest = new ContactRequest();
        setUpContact(contactRequest);
        contactService.saveContact(contactRequest);
        assertEquals(1, contactRepository.count());

        assertThrows(ContactAlreadyExist.class, ()-> contactService.saveContact(contactRequest));
    }

    @Test
    public void testThatContactCanBeEdited_OrUpdated(){
        ContactRequest contactRequest = new ContactRequest();
        setUpContact(contactRequest);
        contactService.saveContact(contactRequest);
        assertEquals(1, contactRepository.count());

        contactRequest.setEmail("NewEmail@gmail.com");
        contactRequest.setName("New name");
        contactRequest.setAddress("2, new address, sabo, lagos");
        contactRequest.setPhoneNumber("09098765432");
        contactService.saveContact(contactRequest);

        Contact updatedContact = contactRepository.findContactByPhoneNumber(contactRequest.getPhoneNumber())
                .orElseThrow(() -> new ContactNotFoundException("Contact not found"));

        assertEquals("NewEmail@gmail.com", updatedContact.getEmail());
        assertEquals("New name", updatedContact.getName());
        assertEquals("2, new address, sabo, lagos", updatedContact.getAddress());
    }
}