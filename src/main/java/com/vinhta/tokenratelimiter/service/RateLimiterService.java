package com.vinhta.tokenratelimiter.service;

import com.vinhta.tokenratelimiter.controller.RateLimitResponseDTO;
import com.vinhta.tokenratelimiter.exception.FailedToAcquireTokenException;
import com.vinhta.tokenratelimiter.exception.RateLimitKeyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RateLimiterService {
    private final TokenBucketService tokenBucketService;

    public RateLimitResponseDTO rateLimit() throws FailedToAcquireTokenException {
        return tokenBucketService.tryAcquireGlobalTokenAndGetRemaining().map(RateLimitResponseDTO::new)
                .orElseThrow(FailedToAcquireTokenException::new);
    }

    public RateLimitResponseDTO rateLimit(String key) throws FailedToAcquireTokenException, RateLimitKeyNotFoundException {
        int keyRemainingTokens = tokenBucketService.tryAcquireTokenForKeyAndGetRemaining(key).orElseThrow(FailedToAcquireTokenException::new);
        int globalRemainingTokens = tokenBucketService.tryAcquireGlobalTokenAndGetRemaining().orElseThrow(FailedToAcquireTokenException::new);

        return new RateLimitResponseDTO(Math.min(globalRemainingTokens, keyRemainingTokens));
    }
}
