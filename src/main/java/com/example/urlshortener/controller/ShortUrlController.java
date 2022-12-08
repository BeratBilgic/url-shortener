package com.example.urlshortener.controller;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.dto.ShortUrlDto;
import com.example.urlshortener.service.ShortUrlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/shortener/")
public class ShortUrlController {

    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
    }

    @GetMapping("/show/{code}")
    public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @NotEmpty @PathVariable String code){
        return ResponseEntity.ok(service.getShortUrlByCode(code));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShortUrlDto> redirectToUrl(@Valid @NotEmpty @PathVariable String code) throws URISyntaxException {
        ShortUrlDto shortUrlDto = service.getShortUrlByCode(code);

        URI uri = new URI(shortUrlDto.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return ResponseEntity.status(HttpStatus.SEE_OTHER).headers(httpHeaders).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShortUrlDto>> getAllShortUrls(){
        return ResponseEntity.ok(service.getAllShortUrls());
    }

    @PostMapping
    public ResponseEntity<ShortUrlDto> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request){
        ShortUrlDto createdShortUrlDto = service.createShortUrl(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}")
                .buildAndExpand(createdShortUrlDto.getCode()).toUri();
        return ResponseEntity.created(location).body(createdShortUrlDto);
    }
}