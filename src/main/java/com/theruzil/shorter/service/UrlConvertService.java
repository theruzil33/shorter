package com.theruzil.shorter.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public interface UrlConvertService {
    String idToString(@Min(1) int id);
    int stringToId(@NotEmpty String str);
}
