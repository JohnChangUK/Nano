package com.nano.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountWeight extends BaseResponse {

    @JsonProperty("weight")
    private String weight;

    public String getWeight() {
        return weight;
    }
}
