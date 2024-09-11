package com.theruzil.shorter.service.impl;

import com.theruzil.shorter.service.UrlConvertService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UrlConvertServiceImpl implements UrlConvertService {
    private final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int new_base = letters.length();

    @Override
    public String idToString(@Min(1) int id) {
        StringBuilder stringBuilder = new StringBuilder();
        while (id > 0) {
            int char_num = id % new_base;
            stringBuilder.append(letters.charAt(char_num));
            id = id / new_base;
        }

        return stringBuilder.reverse().toString();
    }

    @Override
    public int stringToId(@NotEmpty String str) {
        double result = 0;
        int counter = 1;
        for (int i = 0; i < str.length(); i++) {
            int mapped = letters.indexOf(str.charAt(i));
            result = result + mapped * Math.pow(new_base, str.length() - counter);
            counter++;
        }
        return (int) result;
    }
}
