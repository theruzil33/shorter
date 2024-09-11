package com.theruzil.shorter.entity;

import jakarta.persistence.*;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullUrl;

    public Url() { }

    public Url(Integer id, String fullUrl) {
        this.id = id;
        this.fullUrl = fullUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
