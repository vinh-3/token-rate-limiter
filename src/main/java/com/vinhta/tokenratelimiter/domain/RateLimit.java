package com.vinhta.tokenratelimiter.domain;

import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import static com.vinhta.tokenratelimiter.config.Constants.GLOBAL_KEY;

@ConstructorBinding
public record RateLimit (
        @NotEmpty
        String key,
        @Min(1)
        int refillRate,
        @Min(1)
        int capacity
) {

    public boolean isGlobal() {
        return key.equals(GLOBAL_KEY);
    }

}