package com.theruzil.shorter.controller;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UrlResponse get(@PathVariable long id) {
        return urlService.getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody UrlRequest url) {
        UrlResponse result = urlService.createUrl(url);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
