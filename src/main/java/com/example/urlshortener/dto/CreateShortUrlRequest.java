package com.example.urlshortener.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateShortUrlRequest {

    @ApiModelProperty(name = "original url")
    @NotBlank
    private String url;

    @ApiModelProperty(name = "shortUrl code")
    private String code;
}
