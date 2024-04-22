package com.kekwy.mcolc.util;

import com.kekwy.mcolc.vo.ResponseBody;

public class ResponseBuilder {

    private final ResponseBody responseBody = new ResponseBody();

    public ResponseBuilder code(int code) {
        responseBody.setCode(code);
        return this;
    }

    public ResponseBuilder message(String message) {
        responseBody.setMessage(message);
        return this;
    }

    public ResponseBuilder payload(Object payload) {
        responseBody.setPayload(payload);
        return this;
    }

    public ResponseBody build() {
        return responseBody;
    }

}
