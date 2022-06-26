package com.localgp.localgp.model;

//import javax.validation.constraints.Size;

import com.localgp.localgp.customValidations.UsernameDistinct;
import com.localgp.localgp.entity.Genderoptions;
import com.localgp.localgp.entity.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationModel {
        private Long id;
        private String email;

        @UsernameDistinct
        private String username;

        private String password;
        private String permissions = "";
        private String addRole;

        private String firstName;

        private String lastName;

        private String mobileNumber;

        private LocalDate dateOfBirth;

        @Enumerated(value = EnumType.STRING)
        private Genderoptions genderoptions;

        private String address;
//
        public UserRegistrationModel(UserAuth userAuth){
                this.setId(userAuth.getId());
                this.setEmail(userAuth.getEmail());
                this.setUsername(userAuth.getUsername());
//                this.setPassword(userAuth.getPassword());
                this.setPermissions(userAuth.getPermissions());
                this.setAddRole(userAuth.getRoles());
                this.setFirstName(userAuth.getFirstName());
                this.setLastName(userAuth.getLastName());
                this.setMobileNumber(userAuth.getMobileNumber());
                this.setDateOfBirth(userAuth.getDateOfBirth());
                this.setGenderoptions(userAuth.getGenderoptions());
                this.setAddress(userAuth.getAddress());
        }
}
