package com.vinhta.tokenratelimiter.config.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidRateLimitsValidator.class)
public @interface ValidRateLimits {

    String message() default "rate_limits must include a 'GLOBAL' key. Additionally, the 'refill_rate' and 'capacity' of "
            + "other rate limits must not exceed the 'GLOBAL' rate limit.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}