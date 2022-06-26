package com.localgp.localgp.customValidations;

import com.localgp.localgp.entity.UserAuth;
import com.localgp.localgp.model.dtoModel.UpdatePasswordModel;
import com.localgp.localgp.service.UserPrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OldPasswordValidationImpl implements ConstraintValidator<OldPasswordValidation, Object> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPrincipalDetails userPrincipalDetails;

    @Override
    public void initialize(OldPasswordValidation constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UpdatePasswordModel updatePasswordModel = (UpdatePasswordModel) o;
        boolean isValid = false;
        UserAuth userAuth = userPrincipalDetails.getUserById(updatePasswordModel.getUserId());
        if (passwordEncoder.matches(updatePasswordModel.getOldPassword(), userAuth.getPassword())) {
            isValid = true;
        }
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("newPassword").addConstraintViolation();
        }
        return isValid;
    }
}
