package com.theezy.controller;

import com.theezy.dto.response.CallerDetailsResponse;
import com.theezy.services.CallerDetailsVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/phone")
public class CallerDetailsVerificationController {

    @Autowired
    private CallerDetailsVerificationService callerDetailsVerificationService;

    @GetMapping("{number}")
    public Mono<CallerDetailsResponse> validatePhoneNumber(@PathVariable("number") String phoneNumber){
        return callerDetailsVerificationService.validatePhoneNumber(phoneNumber);
    }
}
