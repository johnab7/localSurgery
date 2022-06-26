package com.localgp.localgp.model.dtoModel;


import com.localgp.localgp.customValidations.OldPasswordValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@OldPasswordValidation
public class UpdatePasswordModel {

        private long userId;

        private String oldPassword;

        private String newPassword;

}
