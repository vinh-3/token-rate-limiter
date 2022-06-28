package com.vinhta.tokenratelimiter.domain;

import lombok.Getter;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class TokenBucket {

    private final int capacity;

    private final int refillRate;

    private final Lock lock = new ReentrantLock();

    private volatile int tokens;

    private TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
    }

    public Optional<Integer> tryAcquireTokenAndGetRemaining() {
        // returns the remaining number of tokens once a token is acquired else empty optional
        try {
            lock.lock();

            return tokens > 0
                    ? Optional.of(decrementTokensAndGetRemaining())
                    : Optional.empty();

        } finally {
            lock.unlock();
        }
    }

    public void refill() {
        try {
            lock.lock();
            tokens = Math.min(tokens + refillRate, capacity);

        } finally {
            lock.unlock();
        }
    }

    private int decrementTokensAndGetRemaining() {
        tokens--;
        return tokens;
    }

    public static TokenBucket from(RateLimit rateLimit) {
        return new TokenBucket(rateLimit.capacity(), rateLimit.refillRate());
    }

}
