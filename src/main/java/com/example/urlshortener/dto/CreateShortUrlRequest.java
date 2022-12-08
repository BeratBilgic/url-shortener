package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateShortUrlRequest {

    @NotEmpty
    private String url;

    private String code;

    private LocalDateTime expirationDate;
}
