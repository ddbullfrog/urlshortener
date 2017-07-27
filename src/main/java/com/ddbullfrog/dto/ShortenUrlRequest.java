package com.ddbullfrog.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

public class ShortenUrlRequest {

    @NotNull
    @URL
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
