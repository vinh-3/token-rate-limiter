package com.vinhta.tokenratelimiter.service;

import com.vinhta.tokenratelimiter.config.RateLimitConfig;
import com.vinhta.tokenratelimiter.domain.RateLimit;
import com.vinhta.tokenratelimiter.domain.TokenBucket;
import com.vinhta.tokenratelimiter.exception.RateLimitKeyNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vinhta.tokenratelimiter.config.Constants.GLOBAL_KEY;

@Service
public class TokenBucketService {

    private final Map<String, TokenBucket> tokenBuckets;

    public TokenBucketService(RateLimitConfig rateLimitConfig) {
        this.tokenBuckets = rateLimitConfig.getRateLimits().stream()
                .collect(Collectors.toUnmodifiableMap(RateLimit::key, TokenBucket::from));
    }

    public Optional<Integer> tryAcquireGlobalTokenAndGetRemaining() {
        TokenBucket globalBucket = tokenBuckets.get(GLOBAL_KEY);

        return globalBucket.tryAcquireTokenAndGetRemaining();
    }

    public Optional<Integer> tryAcquireTokenForKeyAndGetRemaining(String key) throws RateLimitKeyNotFoundException {
        if (!tokenBuckets.containsKey(key)) {
            throw new RateLimitKeyNotFoundException();
        }

        TokenBucket tokenBucket = tokenBuckets.get(key);

        return tokenBucket.tryAcquireTokenAndGetRemaining();
    }

    private void refillBuckets() {
        tokenBuckets.values()
                .forEach(TokenBucket::refill);
    }
}
