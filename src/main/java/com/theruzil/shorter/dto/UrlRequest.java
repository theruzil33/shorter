package com.theruzil.shorter.dto;

public class UrlRequest {
    private String fullUrl;

    public UrlRequest() {}

    public UrlRequest(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
