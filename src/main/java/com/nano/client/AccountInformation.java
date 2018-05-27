package com.nano.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInformation extends BaseResponse {
    @JsonProperty("frontier")
    private String frontier;
    @JsonProperty("open_block")
    private String openBlock;
    @JsonProperty("representative_block")
    private String representativeBlock;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("modified_timestamp")
    private String modifiedTimestamp;
    @JsonProperty("block_count")
    private String blockCount;
    private String representative;
    private String weight;
    private String pending;

    public String getFrontier() {
        return frontier;
    }

    public String getOpenBlock() {
        return openBlock;
    }

    public String getRepresentativeBlock() {
        return representativeBlock;
    }

    public String getBalance() {
        return balance;
    }

    public String getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public String getBlockCount() {
        return blockCount;
    }

    public String getRepresentative() {
        return representative;
    }

    public String getWeight() {
        return weight;
    }

    public String getPending() {
        return pending;
    }
}
