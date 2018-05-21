package com.nano.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Balances extends BaseResponse {

    private Map<String, Balance> balances;

    public List<Balance> getBalances() {
        List<Balance> output = new ArrayList<>();
        for (String account : balances.keySet()) {
            Balance balance = balances.get(account);
            balance.setAccount(account);
            output.add(balance);
        }
        return output;
    }
}
