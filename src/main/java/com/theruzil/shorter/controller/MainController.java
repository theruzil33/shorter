package com.theruzil.shorter.controller;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final UrlService urlService;

    @Autowired
    public MainController(UrlService urlService) {
        this.urlService = urlService;
    }
    @GetMapping("/")
    public String mainForm(Model model) {
        model.addAttribute("urlRequest", new UrlRequest());
        model.addAttribute("urls", urlService.findAll());
        return "index";
    }

    @PostMapping("/")
    public String mainSubmit(@ModelAttribute UrlRequest urlRequest, Model model) {
        UrlResponse result = urlService.createUrl(urlRequest);
        model.addAttribute("urls", urlService.findAll());
        return "index";
    }
}
