package com.ddbullfrog.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ShortenURLRequest implements Serializable {

    @NotNull
    @URL
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ShortenURLRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
