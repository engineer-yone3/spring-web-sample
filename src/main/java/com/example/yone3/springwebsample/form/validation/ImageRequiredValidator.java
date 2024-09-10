package com.example.yone3.springwebsample.form.validation;

import com.example.yone3.springwebsample.form.ReviewRegisterForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.thymeleaf.util.StringUtils;

public class ImageRequiredValidator implements ConstraintValidator<ImageRequired, ReviewRegisterForm> {
    @Override
    public boolean isValid(ReviewRegisterForm value, ConstraintValidatorContext context) {
        return !value.getImage().isEmpty() || !StringUtils.isEmpty(value.getImageUrl());
    }
}
