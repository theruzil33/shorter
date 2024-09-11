package com.theruzil.shorter.service;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;

import java.util.List;

public interface UrlService {
    UrlResponse createUrl(UrlRequest fullUrl);
    UrlResponse getById(long id);

    List<UrlResponse> findAll();
}
