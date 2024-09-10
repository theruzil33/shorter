package com.theruzil.shorter.service.impl;

import com.theruzil.shorter.service.ShortService;
import org.springframework.stereotype.Service;

@Service
public class ShortServiceImpl implements ShortService {
    @Override
    public String curtail(String fullStr) {
        return fullStr;
    }
}
