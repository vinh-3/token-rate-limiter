package com.vinhta.tokenratelimiter.e2e.feign;

import com.vinhta.tokenratelimiter.controller.RateLimitResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ratelimiter")
public interface RateLimiterClient {

    @
    public RateLimitResponseDTO rateLimitRequest();

}
