package com.theruzil.shorter.service.impl;

import com.theruzil.shorter.service.BaseUrlService;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class BaseUrlServiceImpl implements BaseUrlService {
    @Override
    public String get(String requestUrl) throws URISyntaxException, MalformedURLException {
        URL url = new URI(requestUrl).toURL();
        String protocol = url.getProtocol();
        String host = url.getHost();
        int port = url.getPort();

        if (port == -1) {
            return String.format("%s://%s/", protocol, host);
        } else {
            return String.format("%s://%s:%d/", protocol, host, port);
        }
    }
}
