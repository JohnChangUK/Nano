package com.nano.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class AvailableSupply extends BaseResponse {

    private String available;

    public String getAvailable() {
        return available;
    }
}
