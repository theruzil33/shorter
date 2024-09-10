package com.theruzil.shorter.entity;

import jakarta.persistence.*;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullUrl;
    @Column(unique = true)
    private String shortUrl;

    public Url() { }

    public Url(Long id, String fullUrl, String shortUrl) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
