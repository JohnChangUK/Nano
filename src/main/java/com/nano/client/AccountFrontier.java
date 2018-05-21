package com.nano.client;

public class AccountFrontier {

    private String address;
    private String block;

    AccountFrontier(String address, String block) {
        this.address = address;
        this.block = block;
    }

    public String getAddress() {
        return address;
    }

    public String getBlock() {
        return block;
    }
}
