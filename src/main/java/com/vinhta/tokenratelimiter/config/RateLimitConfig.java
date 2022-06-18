package com.vinhta.tokenratelimiter.config;

import com.vinhta.tokenratelimiter.config.validation.ValidRateLimits;

import com.vinhta.tokenratelimiter.domain.RateLimit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@ConfigurationProperties(prefix = "token-rate-limiter")
@Getter
@Setter
@Validated
public class RateLimitConfig {

    @ValidRateLimits
    private List<RateLimit> rateLimits;

}
