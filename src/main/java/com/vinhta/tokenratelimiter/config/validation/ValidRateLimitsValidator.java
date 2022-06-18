package com.vinhta.tokenratelimiter.config.validation;

import com.vinhta.tokenratelimiter.domain.RateLimit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class ValidRateLimitsValidator implements ConstraintValidator<ValidRateLimits, List<RateLimit>> {

    @Override
    public boolean isValid(List<RateLimit> rateLimits, ConstraintValidatorContext constraintValidatorContext) {
        Optional<RateLimit> globalRateLimit = findGlobalRateLimit(rateLimits);
        List<RateLimit> nonGlobalRateLimits = findNonGlobalRateLimits(rateLimits);

        if (globalRateLimit.isEmpty()) {
            return false;
        }

        return !anyCapacityOrRefillRateExceedsGlobal(nonGlobalRateLimits, globalRateLimit.get());
    }


    private Optional<RateLimit> findGlobalRateLimit(List<RateLimit> rateLimits) {
        return rateLimits.stream()
                .filter(RateLimit::isGlobal)
                .findFirst();
    }

    private List<RateLimit> findNonGlobalRateLimits(List<RateLimit> rateLimits) {
        return rateLimits.stream()
                .filter(rateLimit -> !rateLimit.isGlobal())
                .toList();
    }

    private boolean anyCapacityOrRefillRateExceedsGlobal(List<RateLimit> nonGlobalRateLimits, RateLimit globalRateLimit) {
        return nonGlobalRateLimits.stream()
                .anyMatch(
                        rateLimit ->
                                rateLimit.refillRate() > globalRateLimit.refillRate()
                                || rateLimit.capacity() > globalRateLimit.capacity()
                );
    }

}
