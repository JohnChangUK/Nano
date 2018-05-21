package com.nano.client;

import java.util.HashMap;
import java.util.Map;

class Request {

    private static final String ACTION_KEY = "action";
    private final String action;
    private final Map<String, Object> map;

    Request(String action, Map<String, Object> map) {
        this.action = action;
        this.map = map;
        this.map.put(ACTION_KEY, action);
    }

    Map<String, Object> getMap() {
        return map;
    }

    public String getAction() {
        return action;
    }

    static Builder action(String action) {
        return new Builder(action);
    }

    static class Builder {

        private final String action;
        private final Map<String, Object> map;

        Builder(String action) {
            this.action = action;
            this.map = new HashMap<>();
        }

        Builder param(String key, Object value) {
            map.put(key, value);
            return this;
        }

        Request build() {
            return new Request(action, map);
        }
    }
}
