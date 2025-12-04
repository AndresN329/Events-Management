package com.riwi.events_management.application.dto.validation;

import com.riwi.events_management.application.dto.request.EventRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, EventRequest> {

    @Override
    public boolean isValid(EventRequest value, ConstraintValidatorContext context) {

        if (value == null) return true;
        if (value.getStartDate() == null || value.getEndDate() == null) return true;

        boolean isValid = value.getStartDate().isBefore(value.getEndDate());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "startDate debe ser anterior a endDate"
                    )
                    .addPropertyNode("startDate")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
