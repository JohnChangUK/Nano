package org.nano;

import com.google.common.base.Objects;
import com.nano.client.BaseResponse;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class NanoHttpClient {

    private String url;
    private String API_KEY;

    NanoHttpClient(String url, String apiKey) {
        this.url = url;
        this.API_KEY = apiKey;
    }

    public BaseResponse rpcRequest(String entity, Class<? extends BaseResponse> baseResponseClass) {

        return ClientBuilder.newClient()
                .target(url).request()
                .accept(APPLICATION_JSON_TYPE)
                .header("Authorization", API_KEY)
                .post(Entity.entity(entity, APPLICATION_JSON_TYPE))
                .readEntity(baseResponseClass);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NanoHttpClient that = (NanoHttpClient) o;
        return Objects.equal(url, that.url) &&
                Objects.equal(API_KEY, that.API_KEY);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, API_KEY);
    }
}
