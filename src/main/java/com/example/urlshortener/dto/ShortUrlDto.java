package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortUrlDto {
    private Long id;
    private String url;
    private String code;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
}
