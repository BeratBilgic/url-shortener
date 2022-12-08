package com.example.urlshortener.service;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.dto.ShortUrlDto;
import com.example.urlshortener.dto.converter.CreateShortUrlRequestConverter;
import com.example.urlshortener.dto.converter.ShortUrlDtoConverter;
import com.example.urlshortener.exception.CodeAlreadyExists;
import com.example.urlshortener.exception.ShortUrlNotFoundException;
import com.example.urlshortener.model.ShortUrl;
import com.example.urlshortener.repository.ShortUrlRepository;
import com.example.urlshortener.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortUrlService {

    private final ShortUrlRepository repository;
    private final CreateShortUrlRequestConverter requestConverter;
    private final RandomStringGenerator randomStringGenerator;
    private final ShortUrlDtoConverter dtoConverter;

    public ShortUrlService(ShortUrlRepository repository,
                           CreateShortUrlRequestConverter converter,
                           RandomStringGenerator randomStringGenerator,
                           ShortUrlDtoConverter dtoConverter) {
        this.repository = repository;
        this.requestConverter = converter;
        this.randomStringGenerator = randomStringGenerator;
        this.dtoConverter = dtoConverter;
    }

    protected ShortUrl findShortUrlByCode(String code){
        return repository.findAllByCode(code).orElseThrow(
                ()-> new ShortUrlNotFoundException("url not found!")
        );
    }

    public ShortUrlDto getShortUrlByCode(String code){
        return dtoConverter.convertToDto(findShortUrlByCode(code));
    }

    public List<ShortUrlDto> getAllShortUrls(){
        return repository.findAll()
                .stream()
                .map(dtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public ShortUrlDto createShortUrl(CreateShortUrlRequest request){
        if (request.getCode() == null || request.getCode().isEmpty()){
            request.setCode(generateCode());
        }else if (repository.findAllByCode(request.getCode()).isPresent()){
            throw new CodeAlreadyExists("code already exists");
        }
        request.setCode(request.getCode().toUpperCase());

        return dtoConverter.convertToDto(repository.save(requestConverter.convertToEntity(request)));
    }

    private String generateCode(){
        String code;
        do {
            code = randomStringGenerator.generateRandomString();
        }while (repository.findAllByCode(code).isPresent());
        return code;
    }
}
