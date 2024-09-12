package com.theruzil.shorter.dto;

import com.theruzil.shorter.validator.url.ValidUrl;

public class UrlRequest {
    @ValidUrl
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
