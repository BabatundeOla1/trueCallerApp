package com.theezy.utils.exceptions;

public class ContactAlreadyExist extends RuntimeException{
    public ContactAlreadyExist(String message){
        super(message);
    }
}
