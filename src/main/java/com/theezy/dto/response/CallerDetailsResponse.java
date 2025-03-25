package com.theezy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallerDetailsResponse {
    private boolean valid;
    private String number;
    private String local_format;
    private String international_format;
    private String country_prefix;
    private String countryCode;
    private String countryName;
    private String location;
    private String carrier;
    private String lineType;
}
