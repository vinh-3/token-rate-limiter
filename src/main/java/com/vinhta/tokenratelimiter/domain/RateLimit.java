package com.vinhta.tokenratelimiter.domain;

import org.springframework.boot.context.properties.ConstructorBinding;

import static com.vinhta.tokenratelimiter.config.Constants.GLOBAL_KEY;

@ConstructorBinding
public record RateLimit (
        String key,
        int refillRate,
        int capacity
) {

    public boolean isGlobal() {
        return key.equals(GLOBAL_KEY);
    }

}