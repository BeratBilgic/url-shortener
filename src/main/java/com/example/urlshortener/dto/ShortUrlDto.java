package com.example.urlshortener.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortUrlDto {
    @ApiModelProperty(name = "shortUrl id")
    private Long id;
    @ApiModelProperty(name = "original url")
    private String url;
    @ApiModelProperty(name = "shortUrl code")
    private String code;
}
