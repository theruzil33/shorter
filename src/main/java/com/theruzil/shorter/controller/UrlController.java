package com.theruzil.shorter.controller;

import com.theruzil.shorter.entity.Url;
import com.theruzil.shorter.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlController {
    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{id}")
    public Url get(@PathVariable long id) {
        return urlService.getById(id);
    }

    @PostMapping("/")
    public Url create(@RequestBody Url url) {
        return urlService.createUrl(url.getFullUrl());
    }
}
