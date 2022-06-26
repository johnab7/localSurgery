package com.localgp.localgp.entity;

import com.localgp.localgp.model.dtoModel.PatientRegistrationModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "id_patient")
//@Table(name = "patients", uniqueConstraints = {@UniqueConstraint(name = "patient_email_unique", columnNames = "email")})
public class Patient extends UserAuth implements Serializable {

    private int weight;
    private int height;
    private int nhsNumber;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    private List<MedicalRecord> medicalRecord;


    public Patient() {}

    public Patient(String username, String password, String roles, String permissions, String firstName, String lastName, String email, String mobileNumber, LocalDate dateOfBirth, Genderoptions genderoptions, String address, int weight, int height, int nhsNumber) {
        super(username, password, roles, permissions, firstName, lastName, email, mobileNumber, dateOfBirth, genderoptions, address);
        this.weight = weight;
        this.height = height;
        this.nhsNumber = nhsNumber;
    }
    //    public Patient(GroupedUserRegistrationModel patientRegistrationModel, String encryptedPassword, String role) {
//        super(patientRegistrationModel, encryptedPassword, role);
//        this.nhsNumber = patientRegistrationModel.getNHSNumber();
//        this.height= patientRegistrationModel.getHeight();
//        this.weight= patientRegistrationModel.getWeight();
//
//    }

    public Patient(PatientRegistrationModel patientRegistrationModel, String encryptedPassword, String role) {
        super(patientRegistrationModel, encryptedPassword, role);
        this.setNhsNumber(patientRegistrationModel.getNhsNumber());
        this.setHeight(patientRegistrationModel.getHeight());
        this.setWeight(patientRegistrationModel.getWeight());
    }

    public void updatePatient(PatientRegistrationModel patientRegistrationModel) {
        super.updateUser(patientRegistrationModel);
        this.nhsNumber = patientRegistrationModel.getNhsNumber();
        this.height= patientRegistrationModel.getHeight();
        this.weight= patientRegistrationModel.getWeight();
    }

}
