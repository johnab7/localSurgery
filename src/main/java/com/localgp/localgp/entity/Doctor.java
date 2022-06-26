package com.localgp.localgp.entity;

import com.localgp.localgp.model.DoctorDetailsModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctors")
@PrimaryKeyJoinColumn(name = "id_doctor")
public class Doctor extends UserAuth implements Serializable{

    private final String hospitalName="UOL";

    private String department;

    private String roomNumber;

    @OneToOne(mappedBy = "doctor", cascade = {CascadeType.ALL})
    private DoctorSchedule doctorSchedule;

//    @OneToOne //(optional = false)
//    @JoinColumn(name = "week_schedule_id", referencedColumnName = "id")
//    private WeekSchedule weekSchedule;

//    @OneToOne(optional = false)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserAuth userAuth;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Patient> patients;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Prescription> prescriptions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<MedicalRecord> medicalRecords;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doctor")
    private Set<Appointment> appointments;


    public Doctor(String username, String password, String roles, String permissions, String firstName, String lastName, String email, String mobileNumber, LocalDate dateOfBirth, Genderoptions genderoptions, String address, String department, String roomNumber) {
        super(username, password, roles, permissions, firstName, lastName, email, mobileNumber, dateOfBirth, genderoptions, address);
        this.department = department;
        this.roomNumber = roomNumber;
    }

    public Doctor(String username, String password, String roles, String permissions, String firstName, String lastName, String email, String mobileNumber, LocalDate dateOfBirth, Genderoptions genderoptions, String address, String department, String roomNumber, DoctorSchedule doctorSchedule) {
        super(username, password, roles, permissions, firstName, lastName, email, mobileNumber, dateOfBirth, genderoptions, address);
        this.department = department;
        this.roomNumber = roomNumber;
        this.doctorSchedule= doctorSchedule;
        doctorSchedule.setDoctor(this);
    }

    public Doctor(DoctorDetailsModel doctorDetailsModel, String encryptedPassword, String role, DoctorSchedule doctorSchedule) {
        super(doctorDetailsModel, encryptedPassword, role);
        this.doctorSchedule = doctorSchedule;
        doctorSchedule.setDoctor(this);
        this.department= doctorDetailsModel.getDepartment();
        this.roomNumber= doctorDetailsModel.getRoomNumber();
    }

    public void updateDoctor(DoctorDetailsModel doctorDetailsModel) {
        super.updateUser(doctorDetailsModel);
        this.department= doctorDetailsModel.getDepartment();
        this.roomNumber= doctorDetailsModel.getRoomNumber();
    }

}