package com.theruzil.shorter.dto;

import com.theruzil.shorter.validator.url.ValidUrl;

public class UrlRequest {
    private int id;
    @ValidUrl
    private String fullUrl;

    public UrlRequest() {}

    public UrlRequest(int id, String fullUrl) {
        this.id = id;
        this.fullUrl = fullUrl;
    }

    public UrlRequest(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
