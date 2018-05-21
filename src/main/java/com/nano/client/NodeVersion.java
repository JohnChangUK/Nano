package com.nano.client;

public class NodeVersion extends BaseResponse {

    private String rpcVersion;
    private String storeVersion;
    private String nodeVendor;

    public String getRpcVersion() {
        return rpcVersion;
    }

    public String getStoreVersion() {
        return storeVersion;
    }

    public String getNodeVendor() {
        return nodeVendor;
    }
}
