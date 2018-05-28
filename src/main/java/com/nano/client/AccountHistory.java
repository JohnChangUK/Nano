package com.nano.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties
public class AccountHistory extends BaseResponse {

    @JsonProperty("history")
    private List<History> history;

    public List<History> getHistory() {
        return history;
    }
}
