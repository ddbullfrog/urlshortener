package com.ddbullfrog.rest;

import com.ddbullfrog.domain.ShortURL;
import com.ddbullfrog.dto.ShortenUrlRequest;
import com.ddbullfrog.repository.ShortURLRepository;
import com.ddbullfrog.util.Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@RestController
public class ShortURLResource {

    @Autowired
    private RedisAtomicInteger redisAtomicInteger;

    @Autowired
    private ShortURLRepository shortURLRepository;

    Random rn = new Random();

    @PostMapping(path = "/urls")
    public ResponseEntity<ShortURL> shorten(@Valid @RequestBody ShortenUrlRequest request) {

        int id = redisAtomicInteger.addAndGet(rn.nextInt(3) + 1);

        ShortURL shortURL = new ShortURL();
        shortURL.setId(id);
        shortURL.setSlug(Codec.encode(id));
        shortURL.setUrl(request.getUrl());
        ShortURL result = shortURLRepository.save(shortURL);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{slug}")
    public void redirectToUrl(@PathVariable String slug, HttpServletResponse httpServletResponse) throws IOException {
        Optional<ShortURL> shortURL = shortURLRepository.findBySlug(slug);
        if (shortURL.isPresent()) {
            httpServletResponse.addHeader(HttpHeaders.LOCATION, shortURL.get().getUrl());
            httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
