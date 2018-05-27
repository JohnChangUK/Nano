package com.nano.client;

public abstract class BaseResponse {

    private String error;

    boolean isSuccess() {
        return error == null;
    }

    String getError() {
        return error;
    }
}
