package com.theezy.config;


import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
//@Getter
@Data
@PropertySource("classpath:.env")
public class NumVerifyConfiguration {

        @Value("${API_ACCESS_KEY}")
        private String apiKey;
}
