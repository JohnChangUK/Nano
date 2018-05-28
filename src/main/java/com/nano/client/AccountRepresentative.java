package com.nano.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class AccountRepresentative extends BaseResponse {

    @JsonProperty("representative")
    private String representative;

    public String getRepresentative() {
        return representative;
    }
}
