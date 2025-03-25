package com.theezy.services;

import com.theezy.config.NumVerifyConfiguration;
import com.theezy.dto.response.CallerDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CallerDetailsVerificationServiceImpl implements CallerDetailsVerificationService{

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private NumVerifyConfiguration numVerifyConfiguration;

    @Override
    public Mono<CallerDetailsResponse> validatePhoneNumber(String phoneNumber) {
        String apiKey = numVerifyConfiguration.getApiKey();

        return webClientBuilder.baseUrl("http://apilayer.net/api").build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/validate")
                        .queryParam("access_apiKey", apiKey)
                        .queryParam("number", phoneNumber)
                        .build())
                        .retrieve()
                .bodyToMono(CallerDetailsResponse.class);
    }
}
