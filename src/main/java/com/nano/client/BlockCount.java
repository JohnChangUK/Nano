package com.nano.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class BlockCount extends BaseResponse {

    private String count;
    private String unchecked;

    public String getCount() {
        return count;
    }

    public String getUnchecked() {
        return unchecked;
    }
}
