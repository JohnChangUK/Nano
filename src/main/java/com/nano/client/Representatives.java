package com.nano.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Representatives extends BaseResponse {

    private Map<String, String> representatives;

    public List<Representative> getRepresentatives() {
        List<Representative> output = new ArrayList<>();
        for (String account : representatives.keySet()) {
            output.add(new Representative(account, representatives.get(account)));
        }
        return output;
    }
}
