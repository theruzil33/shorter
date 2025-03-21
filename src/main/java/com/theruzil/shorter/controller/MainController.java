package com.theruzil.shorter.controller;

import com.theruzil.shorter.dto.UrlRequest;
import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.entity.Url;
import com.theruzil.shorter.service.UrlResponseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

@Controller
public class MainController {
    private final UrlResponseService urlResponseService;
    private final Logger logger;

    @Autowired
    public MainController(
            UrlResponseService urlResponseService,
            Logger logger
    ) {
        this.urlResponseService = urlResponseService;
        this.logger = logger;
    }

    @GetMapping("/to/{pathId}")
    public RedirectView redirect(@PathVariable(value="pathId") String pathId) {
        Url url = urlResponseService.getByShortUrl(pathId);
        RedirectView redirectView = new RedirectView();
        String stringUrl = url.getFullUrl();
        redirectView.setUrl(stringUrl);
        logger.info(String.format("Redirect to %s", stringUrl));
        return redirectView;
    }

    @GetMapping("/")
    public String mainForm(Model model, HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        List<ObjectError> objectErrors = new ArrayList<>();

        return getMainPage(model, requestUrl, objectErrors);
    }

    @PostMapping("/")
    public String mainSubmit(
            @ModelAttribute @Validated final UrlRequest urlRequest, final BindingResult binding,
            Model model, HttpServletRequest request
    ) {
        String requestUrl = request.getRequestURL().toString();
        List<ObjectError> objectErrors = new ArrayList<>();

        if (binding.hasErrors()) {
            objectErrors = binding.getAllErrors();
            for (ObjectError error : objectErrors) {
                logger.error(error.toString());
            }
        } else {
            try {
                urlResponseService.createUrl(urlRequest, requestUrl);
            } catch (Exception e) {
                String errorString = "Ошибка при создании короткой ссылки";
                objectErrors = List.of(new ObjectError("Error", errorString));
                logger.error(errorString);
            }
        }

        return getMainPage(model, requestUrl, objectErrors);
    }

    @DeleteMapping("/")
    public String deleteUrl(
            @ModelAttribute final UrlRequest urlRequest,
            Model model, HttpServletRequest request
    ) {
        String requestUrl = request.getRequestURL().toString();
        List<ObjectError> objectErrors = new ArrayList<>();

        try {
            urlResponseService.deleteById(urlRequest.getId());
        } catch (Exception e) {
            String errorString = "Ошибка при создании короткой ссылки";
            objectErrors = List.of(new ObjectError("Error", errorString));
            logger.error(errorString);
        }

        return getMainPage(model, requestUrl, objectErrors);
    }

    private String getMainPage(Model model, String requestUrl, List<ObjectError> objectErrors) {
        List<UrlResponse> urls = new ArrayList<>();
        try {
            urls = urlResponseService.findAll(requestUrl);
        } catch (Exception e) {
            String errorString = "Ошибка при получениии списка ссылок";
            objectErrors = List.of(new ObjectError("Error", errorString));
            logger.error(errorString);
        }

        model.addAttribute("urlRequest", new UrlRequest());
        model.addAttribute("urls", urls);
        model.addAttribute("errors", objectErrors);
        return "index";
    }
}
