package com.theruzil.shorter.service;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.entity.Url;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface UrlResponseService {
    UrlResponse createUrl(UrlRequest fullUrl, String requestUrl) throws URISyntaxException, MalformedURLException;
    UrlResponse getById(long id, String requestUrl) throws URISyntaxException, MalformedURLException;

    List<UrlResponse> findAll(String requestUrl) throws URISyntaxException, MalformedURLException;
    Url getByShortUrl(String shortUrl);
}
