package com.theruzil.shorter.controller;

import com.theruzil.shorter.dto.UrlResponse;
import com.theruzil.shorter.service.UrlResponseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class MainControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UrlResponseService urlResponseService;

    @Test
    void mainForm() throws Exception {
        Mockito.when(urlResponseService.findAll("http://localhost/")).thenReturn(getUrlResponses());
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(
                model().attribute("urls", hasSize(4))
        ).andExpect(model().attribute("urls", hasItem(
                allOf(
                        hasProperty("shortUrl", is("1")),
                        hasProperty("fullUrl", is("1"))
                )
        ))).andExpect(
                model().attribute("errors", hasSize(0))
        ).andExpect(model().attribute("urlRequest",
                allOf(
                        hasProperty("fullUrl", nullValue())
                )
        ));
    }

    @Test
    void mainFormWithException() throws Exception {
        Mockito.when(urlResponseService.findAll("http://localhost/")).thenThrow(URISyntaxException.class);
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(
                model().attribute("urls", hasSize(0))
        ).andExpect(
                model().attribute("errors", hasSize(1))
        ).andExpect(model().attribute("errors", hasItem(
                allOf(
                        hasProperty("defaultMessage", is("Ошибка при получениии списка ссылок"))
                )
        ))).andExpect(model().attribute("urlRequest",
                allOf(
                        hasProperty("fullUrl", nullValue())
                )
        ));
    }

    @Test
    void mainSubmit() {
    }

    private List<UrlResponse> getUrlResponses() {
        return Arrays.asList(
                new UrlResponse("1", "1"),
                new UrlResponse("2", "2"),
                new UrlResponse("3", "3"),
                new UrlResponse("4", "4")
        );
    }
}