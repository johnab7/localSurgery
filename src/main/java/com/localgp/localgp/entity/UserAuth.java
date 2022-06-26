package com.localgp.localgp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.localgp.localgp.customValidations.UsernameDistinct;
import com.localgp.localgp.model.dtoModel.DoctorDetailsModel;
import com.localgp.localgp.model.dtoModel.PatientRegistrationModel;
import com.localgp.localgp.model.dtoModel.UserRegistrationModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@Entity
@Table(name = "user_auth")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    @UsernameDistinct
    private String username;
    @Column(nullable = false)
    private String password;
    private int active;
    private String roles = "";
    private String permissions = "";


    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Enumerated(value = EnumType.STRING)
    private Genderoptions genderoptions;

    private String address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userAuth")
    private List<Notification> notifications;

//    @OneToMany(mappedBy = "user")
//    private List<Notification> notifications;

    public UserAuth(){}

    public UserAuth(String username,
                    String password,
                    String roles,
                    String permissions){
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    public UserAuth(String username,
                    String password,
                    String roles,
                    String permissions,
                    String firstName,
                    String lastName,
                    String email,
                    String mobileNumber,
                    LocalDate dateOfBirth,
                    Genderoptions genderoptions,
                    String address) {
        this.username = username;
        this.password = password;
        this.active = 1;
        this.roles = roles;
        this.permissions = permissions;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.genderoptions = genderoptions;
        this.address = address;
    }

    //    public UserAuth(PatientRegistrationModel userRegistrationModel, String encryptedPassword, String role) {
//        this.setUsername(userRegistrationModel.getUsername());
//        this.setFirstName(userRegistrationModel.getFirstName());
//        this.setLastName(userRegistrationModel.getLastName());
//        this.setEmail(userRegistrationModel.getEmail());
//        this.setDateOfBirth(userRegistrationModel.getDateOfBirth());
//        this.setGenderoptions(userRegistrationModel.getGenderoptions());
//        this.setAddress(userRegistrationModel.getAddress());
//        this.setMobileNo(userRegistrationModel.getMobileNo());
//        this.password = encryptedPassword;
//        this.roles = role;
//    }
//    public UserAuth(DoctorDetailsModel userRegistrationModel, String encryptedPassword, String role) {
//        this.setUsername(userRegistrationModel.getUsername());
//        this.setFirstName(userRegistrationModel.getFirstName());
//        this.setLastName(userRegistrationModel.getLastName());
//        this.setEmail(userRegistrationModel.getEmail());
//        this.setDateOfBirth(userRegistrationModel.getDateOfBirth());
//        this.setGenderoptions(userRegistrationModel.getGenderoptions());
//        this.setAddress(userRegistrationModel.getAddress());
//        this.setMobileNo(userRegistrationModel.getMobileNo());
//        this.password = encryptedPassword;
//        this.roles = role;
//    }
//public UserAuth(GroupedUserRegistrationModel userRegistrationModel, String encryptedPassword, String role) {
//
//    this.setUsername(userRegistrationModel.getUsername());
//    this.setFirstName(userRegistrationModel.getFirstName());
//    this.setLastName(userRegistrationModel.getLastName());
//    this.setEmail(userRegistrationModel.getEmail());
//    this.setDateOfBirth(userRegistrationModel.getDateOfBirth());
//    this.setGenderoptions(userRegistrationModel.getGenderoptions());
//    this.setAddress(userRegistrationModel.getAddress());
//    this.setMobileNumber(userRegistrationModel.getMobileNumber());
//    this.password = encryptedPassword;
//    this.roles = role;
//    this.active=1;
//
//
//}
    public UserAuth(UserRegistrationModel userRegistrationModel, String encryptedPassword, String role) {
        this.setUsername(userRegistrationModel.getUsername());
        this.setFirstName(userRegistrationModel.getFirstName());
        this.setLastName(userRegistrationModel.getLastName());
        this.setEmail(userRegistrationModel.getEmail());
        this.setDateOfBirth(userRegistrationModel.getDateOfBirth());
        this.setGenderoptions(userRegistrationModel.getGenderoptions());
        this.setAddress(userRegistrationModel.getAddress());
        this.setMobileNumber(userRegistrationModel.getMobileNumber());
        this.setPassword(encryptedPassword);
        this.setRoles(role);
        setActive(1);
    }

    public void updateUser(UserRegistrationModel userRegistrationModel) {
        this.setEmail(userRegistrationModel.getEmail());
        this.setFirstName(userRegistrationModel.getFirstName());
        this.setLastName(userRegistrationModel.getLastName());
        this.setMobileNumber(userRegistrationModel.getMobileNumber());
        this.setAddress(userRegistrationModel.getAddress());
//        this.setStreet(userRegistrationModel.getStreet());
//        this.setPostcode(userRegistrationModel.getPostcode());
    }

//    public void updateUser(PatientRegistrationModel patientRegistrationModel){
//        this.setEmail(patientRegistrationModel.getEmail());
//        this.setFirstName(patientRegistrationModel.getFirstName());
//        this.setLastName(patientRegistrationModel.getLastName());
//        this.setMobileNo(patientRegistrationModel.getMobileNo());
//        this.setAddress(patientRegistrationModel.getAddress());
//    }
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

}
