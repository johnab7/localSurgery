package com.localgp.localgp.customValidations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameDistinctImplementation.class)
public @interface UsernameDistinct {
    String message() default "Sorry! Username is taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
