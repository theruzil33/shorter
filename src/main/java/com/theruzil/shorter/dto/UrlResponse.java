package com.theruzil.shorter.dto;

import com.theruzil.shorter.validator.url.ValidUrl;

public class UrlResponse {
    @ValidUrl
    private String shortUrl;
    @ValidUrl
    private String fullUrl;

    public UrlResponse() {}

    public UrlResponse(String shortUrl, String fullUrl) {
        this.shortUrl = shortUrl;
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
