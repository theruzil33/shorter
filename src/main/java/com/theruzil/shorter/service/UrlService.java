package com.theruzil.shorter.service;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.entity.Url;

public interface UrlService {
    UrlResponse createUrl(UrlRequest fullUrl);
    Url getById(long id);
}
