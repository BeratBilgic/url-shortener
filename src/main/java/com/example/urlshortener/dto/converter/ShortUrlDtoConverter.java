package com.example.urlshortener.dto.converter;

import com.example.urlshortener.dto.ShortUrlDto;
import com.example.urlshortener.model.ShortUrl;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlDtoConverter {

    public ShortUrlDto convertToDto(ShortUrl from){
        return ShortUrlDto.builder()
                .id(from.getId())
                .url(from.getUrl())
                .code(from.getCode())
                .creationDate(from.getCreationDate())
                .expirationDate(from.getExpirationDate())
                .build();
    }
}
