package com.vinhta.tokenratelimiter.e2e.feign;

import com.vinhta.tokenratelimiter.controller.RateLimitResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ratelimiter")
public interface RateLimiterClient {

    @PostMapping(path = "/v1/rate-limit")
    public RateLimitResponseDTO rateLimitGlobalRequest();

    @PostMapping(path = "v1/rate-limit")
    public RateLimitResponseDTO rateLimitKeyedRequest(@RequestParam(name = "key") String key);

}
