package com.vinhta.tokenratelimiter.controller;

import com.vinhta.tokenratelimiter.exception.FailedToAcquireTokenException;
import com.vinhta.tokenratelimiter.exception.RateLimitKeyNotFoundException;
import com.vinhta.tokenratelimiter.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RateLimitController {

    private final RateLimiterService rateLimiterService;

    @PostMapping(path = "/v1/rate-limit", produces = MediaType.APPLICATION_JSON_VALUE)
    public RateLimitResponseDTO rateLimit(@RequestParam(value = "key", required = false) Optional<String> key) {
        return key.map(this::rateLimitKeyedRequest).orElseGet(this::rateLimitGlobalRequest);
    }

    private RateLimitResponseDTO rateLimitGlobalRequest() {
        try {
            return rateLimiterService.rateLimit();
        } catch (FailedToAcquireTokenException e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    private RateLimitResponseDTO rateLimitKeyedRequest(String key) {
        try {
            return rateLimiterService.rateLimit(key);
        } catch (FailedToAcquireTokenException e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        } catch (RateLimitKeyNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
