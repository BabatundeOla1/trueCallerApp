package com.theezy.services;

import com.theezy.dto.request.CallerDetailsRequest;
import com.theezy.dto.response.CallerDetailsResponse;
import reactor.core.publisher.Mono;

public interface CallerDetailsVerificationService {

Mono<CallerDetailsResponse> validatePhoneNumber(String phoneNumber);
}
