package com.example.urlshortener.dto.converter;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.model.ShortUrl;
import org.springframework.stereotype.Component;

@Component
public class CreateShortUrlRequestConverter {

    public ShortUrl convertToEntity(CreateShortUrlRequest from){
            return ShortUrl.builder()
                    .code(from.getCode())
                    .url(from.getUrl())
                    .build();
    }
}
