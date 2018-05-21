package com.nano.client;

class ExistenceResponse extends BaseResponse {

    private String exists;

    public boolean exists() {
        return "1".equals(exists);
    }
}
