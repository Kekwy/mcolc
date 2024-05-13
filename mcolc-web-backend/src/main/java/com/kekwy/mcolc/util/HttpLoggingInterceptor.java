package com.kekwy.mcolc.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public @NotNull ClientHttpResponse intercept(@NotNull HttpRequest request, byte @NotNull [] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.println("============== Request ==============");
        System.out.println("URI         : " + request.getURI());
        System.out.println("Method      : " + request.getMethod());
        System.out.println("Headers     : " + request.getHeaders());
        System.out.println("Request body: " + new String(body, StandardCharsets.UTF_8));
        System.out.println("====================================");
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        System.out.println("============== Response =============");
        System.out.println("Status code  : " + response.getStatusCode());
        System.out.println("Status text  : " + response.getStatusText());
        System.out.println("Headers      : " + response.getHeaders());
        System.out.println("Response body: " + getResponseBody(response));
        System.out.println("====================================");
    }

    private String getResponseBody(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
//        response.getBody().mark(8192);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            inputStringBuilder.append(line);
        }
//        response.getBody().reset();
        return inputStringBuilder.toString();
    }
}
