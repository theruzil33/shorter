package com.theruzil.shorter.service;

import com.theruzil.shorter.entity.Url;

public interface UrlService {
    Url createUrl(String fullUrl);
    Url getById(long id);
}
