package com.example.yone3.springwebsample.form.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageRequiredValidator.class)
public @interface ImageRequired {
    String message() default "画像URLもしくは画像ファイルをアップロードしてください";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
