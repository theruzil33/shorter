package com.theruzil.shorter.service.impl;

import com.theruzil.shorter.entity.Url;
import com.theruzil.shorter.repository.UrlRepository;
import com.theruzil.shorter.service.ShortService;
import com.theruzil.shorter.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final ShortService shortService;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, ShortService shortService) {
        this.urlRepository = urlRepository;
        this.shortService = shortService;
    }

    @Override
    public Url createUrl(String fullUrl) {
        String shortUrl = shortService.curtail(fullUrl);
        Url url = new Url();
        url.setFullUrl(fullUrl);
        url.setShortUrl(shortUrl);
        return urlRepository.save(url);
    }

    @Override
    public Url getById(long id) {
        return urlRepository.findById(id);
    }
}
