package com.riwi.events_management.application.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

    String message() default "Rango de fechas inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
