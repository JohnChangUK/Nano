package com.nano.client;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

class HttpClient {

    private final OkHttpClient client = new OkHttpClient();
    private final String host;

    HttpClient(String host) {
        this.host = host;
    }

    String post(String body) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder()
            .post(RequestBody.create(MediaType.parse("application/json"), body))
            .url(host)
            .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
