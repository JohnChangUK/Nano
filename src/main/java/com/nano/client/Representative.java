package com.nano.client;

public class Representative {

    private String address;
    private String amount;

    Representative(String address, String amount) {
        this.address = address;
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    void setAmount(String amount) {
        this.amount = amount;
    }
}
