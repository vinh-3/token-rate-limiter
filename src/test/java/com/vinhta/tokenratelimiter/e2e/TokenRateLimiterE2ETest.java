package com.vinhta.tokenratelimiter.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TokenRateLimiterE2ETest {

	@Test
	void rateLimitGlobalRequest_whenTokensRemaining_returnRemainingTokens() {
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
