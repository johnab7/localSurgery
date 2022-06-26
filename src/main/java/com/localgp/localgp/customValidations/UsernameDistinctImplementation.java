package com.localgp.localgp.customValidations;

import com.localgp.localgp.service.UserPrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Null;

public class UsernameDistinctImplementation implements ConstraintValidator<UsernameDistinct, Object> {

    @Autowired
    private UserPrincipalDetails userPrincipalDetails;

    @Override
    public void initialize(final UsernameDistinct constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        String userName = (String) obj;
        return !userPrincipalDetails.checkUsername(userName);
    }

}