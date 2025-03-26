package com.theezy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallerDetailsResponse {
    private boolean valid;
//    @JsonProperty("number")
    private String number;

    @JsonProperty("local_format")
    private String localFormat;

    @JsonProperty("international_format")
    private String internationalFormat;

    @JsonProperty("country_prefix")
    private String countryPrefix;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

//    @JsonProperty("location")
    private String location;
//    @JsonProperty("carrier")
    private String carrier;

    @JsonProperty("line_type")
    private String lineType;
//    private boolean success;

}
