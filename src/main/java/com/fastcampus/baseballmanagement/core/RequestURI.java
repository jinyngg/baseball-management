package com.fastcampus.baseballmanagement.core;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RequestURI {

    private String uri;
    private Map<String, String> param;

    public RequestURI(String uri) {
        this.uri = setUri(uri);
    }

    private String setUri(String uri) {
        String[] uriParam = uri.split("\\?");
        if (uriParam.length != 1) {
            setParam(uriParam[1]);
        }
        return uriParam[0];
    }

    private void setParam(String uriParam) {
        param = new HashMap<>();
        String[] parameters = uriParam.split("&");
        for (String parameter : parameters) {
            String[] values = parameter.split("=");
            param.put(values[0], values[1]);
        }
    }

}
