package com.nano.client;

public class AccountInformation extends BaseResponse {

    private String frontier;
    private String openBlock;
    private String representativeBlock;
    private String balance;
    private String modifiedTimestamp;
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
