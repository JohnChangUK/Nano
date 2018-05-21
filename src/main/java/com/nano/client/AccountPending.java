package com.nano.client;

import java.util.List;

public class AccountPending extends BaseResponse {

    private String address;
    private List<String> blocks;

    AccountPending(String address, List<String> blocks) {
        this.address = address;
        this.blocks = blocks;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getBlocks() {
        return blocks;
    }
}
