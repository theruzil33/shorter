package com.theruzil.shorter.service.impl;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.entity.Url;
import com.theruzil.shorter.repository.UrlRepository;
import com.theruzil.shorter.service.BaseUrlService;
import com.theruzil.shorter.service.StringConvertService;
import com.theruzil.shorter.service.UrlResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlResponseServiceImpl implements UrlResponseService {
    private final UrlRepository urlRepository;
    private final StringConvertService stringConvertService;
    private final BaseUrlService baseUrlService;

    @Autowired
    public UrlResponseServiceImpl(
            UrlRepository urlRepository, StringConvertService stringConvertService, BaseUrlService baseUrlService
    ) {
        this.urlRepository = urlRepository;
        this.stringConvertService = stringConvertService;
        this.baseUrlService = baseUrlService;
    }

    @Override
    public UrlResponse createUrl(UrlRequest fullUrl, String requestUrl) throws URISyntaxException, MalformedURLException {
        Url exist = getIfExist(fullUrl);
        if (exist != null) {
            return new UrlResponse(createShortUrl(exist.getId(), requestUrl), exist.getFullUrl());
        }
        Url url = new Url();
        url.setFullUrl(fullUrl.getFullUrl());
        url = urlRepository.save(url);
        return new UrlResponse(createShortUrl(url.getId(), requestUrl), url.getFullUrl());
    }

    @Override
    public UrlResponse getById(long id, String requestUrl) throws URISyntaxException, MalformedURLException {
        Url url = urlRepository.findById(id);
        return new UrlResponse(createShortUrl(url.getId(), requestUrl), url.getFullUrl());
    }

    private Url getIfExist(UrlRequest urlRequest) {
        List<Url> urls = urlRepository.findByFullUrl(urlRequest.getFullUrl());
        if (urls != null && !urls.isEmpty()) {
            return urls.get(0);
        }
        return null;
    }

    private String createShortUrl(int id, String requestUrl) throws URISyntaxException, MalformedURLException {
        String baseUrl = baseUrlService.get(requestUrl);
        return baseUrl + stringConvertService.idToString(id);
    }

    public List<UrlResponse> findAll(String requestUrl) throws URISyntaxException, MalformedURLException {
        List<Url> urls = urlRepository.findAll();
        List<UrlResponse> urlResponses = new ArrayList<>();
        for (Url url : urls) {
            UrlResponse urlResponse = new UrlResponse(createShortUrl(url.getId(), requestUrl), url.getFullUrl());
            urlResponses.add(urlResponse);
        }
        return urlResponses;
    }
}
