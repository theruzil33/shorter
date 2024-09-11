package com.theruzil.shorter.service.impl;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.entity.Url;
import com.theruzil.shorter.repository.UrlRepository;
import com.theruzil.shorter.service.UrlConvertService;
import com.theruzil.shorter.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final UrlConvertService urlConvertService;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, UrlConvertService urlConvertService) {
        this.urlRepository = urlRepository;
        this.urlConvertService = urlConvertService;
    }

    @Override
    public UrlResponse createUrl(UrlRequest fullUrl) {
        Url exist = getIfExist(fullUrl);
        if (exist != null) {
            return new UrlResponse(urlConvertService.idToString(exist.getId()));
        }
        Url url = new Url();
        url.setFullUrl(fullUrl.getFullUrl());
        url = urlRepository.save(url);
        return new UrlResponse(urlConvertService.idToString(url.getId()));
    }

    @Override
    public Url getById(long id) {
        return urlRepository.findById(id);
    }

    private Url getIfExist(UrlRequest urlRequest) {
        List<Url> urls = urlRepository.findByFullUrl(urlRequest.getFullUrl());
        if (urls != null && !urls.isEmpty()) {
            return urls.get(0);
        }
        return null;
    }
}
