package com.nano.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountsFrontiers extends BaseResponse {

    private Map<String, String> frontiers;

    public List<AccountFrontier> getFrontiers() {
        List<AccountFrontier> output = new ArrayList<>();
        for (String address : frontiers.keySet()) {
            output.add(new AccountFrontier(address, frontiers.get(address)));
        }
        return output;
    }
}
