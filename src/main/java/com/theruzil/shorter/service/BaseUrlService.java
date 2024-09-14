package com.theruzil.shorter.service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface BaseUrlService {
    String get(String requestUrl) throws URISyntaxException, MalformedURLException;
}
