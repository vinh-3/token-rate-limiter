package com.vinhta.tokenratelimiter.e2e;

import com.vinhta.tokenratelimiter.e2e.feign.RateLimiterClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TokenRateLimiterE2ETest {



	@Autowired
	private RateLimiterClient rateLimiterClient;

	@Test
	void rateLimitGlobalRequest_whenTokensRemaining_returnRemainingTokens() {
		rateLimiterClient.rateLimitGlobalRequest();
	}

	@Test
	void rateLimitGlobalRequest_whenNoTokensRemaining_throws429() {
	}

	@Test
	void rateLimitKeyedRequest_whenTokensRemaining_returnRemainingTokens() {
	}

	@Test
	void rateLimitKeyedRequest_whenNoTokensRemaining_throws429() {
	}

	@Test
	void rateLimitKeyedRequest_withInvalidKey_throws404() {
	}

}
