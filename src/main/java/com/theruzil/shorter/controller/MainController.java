package com.theruzil.shorter.controller;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.service.UrlResponseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private final UrlResponseService urlResponseService;

    @Autowired
    public MainController(UrlResponseService urlResponseService) {
        this.urlResponseService = urlResponseService;
    }
    @GetMapping("/")
    public String mainForm(Model model, HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        List<UrlResponse> urls = new ArrayList<>();
        List<ObjectError> objectErrors = new ArrayList<>();

        return getMainPage(model, requestUrl, urls, objectErrors);
    }

    @PostMapping("/")
    public String mainSubmit(
            @ModelAttribute @Validated final UrlRequest urlRequest, final BindingResult binding,
            Model model, HttpServletRequest request
    ) {
        String requestUrl = request.getRequestURL().toString();
        List<UrlResponse> urls = new ArrayList<>();
        List<ObjectError> objectErrors = new ArrayList<>();

        if (binding.hasErrors()) {
            objectErrors = binding.getAllErrors();
        } else {
            try {
                urlResponseService.createUrl(urlRequest, requestUrl);
            } catch (Exception e) {
                objectErrors = List.of(new ObjectError("Error", "Ошибка при создании короткой ссылки"));
            }
        }

        return getMainPage(model, requestUrl, urls, objectErrors);
    }

    private String getMainPage(Model model, String requestUrl, List<UrlResponse> urls, List<ObjectError> objectErrors) {
        try {
            urls = urlResponseService.findAll(requestUrl);
        } catch (Exception e) {
            objectErrors = List.of(new ObjectError("Error", "Ошибка при получениии списка ссылок"));
        }

        model.addAttribute("urlRequest", new UrlRequest());
        model.addAttribute("urls", urls);
        model.addAttribute("errors", objectErrors);
        return "index";
    }
}
