package com.theezy.dto.request;

import com.theezy.data.models.Contact;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class UserRegisterRequest {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private Contact contact;
}
