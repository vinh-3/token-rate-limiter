package com.vinhta.tokenratelimiter.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


public class TokenBucketTest {

    private static final String BUCKET_KEY = "test_key";

    private static final int CAPACITY = 2;

    private static final int REFILL_RATE = 2;

    private TokenBucket tokenBucket;

    @BeforeEach
    public void setUp() {
        var rateLimit = new RateLimit(BUCKET_KEY, CAPACITY, REFILL_RATE);
        tokenBucket = TokenBucket.from(rateLimit);
    }

    @Test
    public void refillBucket_whenEmpty_capacityEqualsRefillRate() {
        // when
        tokenBucket.refill();
        var capacity = tokenBucket.getCapacity();

        // then
        assertThat(capacity).isEqualTo(REFILL_RATE);
    }

    @Test
    public void refillBucket_whenAlreadyRefilled_capacityNotExceeded() {
        // given
        tokenBucket.refill();

        // when
        tokenBucket.refill();
        var capacity = tokenBucket.getCapacity();

        // then
        assertThat(capacity).isLessThanOrEqualTo(CAPACITY);
    }

    @Test
    public void tryAcquireToken_whenNoTokensRemaining_returnEmptyOptional() {
        // when
        var result = tokenBucket.tryAcquireTokenAndGetRemaining();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void tryAcquireToken_whenTokensRemaining_returnRemainingTokens() {
        // given
        tokenBucket.refill();

        // when
        var result = tokenBucket.tryAcquireTokenAndGetRemaining();

        // then
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(CAPACITY - 1);
    }
}
