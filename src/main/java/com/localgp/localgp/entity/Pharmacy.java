package com.localgp.localgp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pharmacy")
@PrimaryKeyJoinColumn(name = "id_pharmacy")
public class Pharmacy extends UserAuth implements Serializable {

    private String pharmacyName;
    private String pharmacyAddress;

    @OneToMany(mappedBy = "pharmacy",cascade = {CascadeType.ALL})
    private List<Prescription> prescription;

    public Pharmacy(String username, String password, String roles, String permissions, String firstName, String lastName, String email, String mobileNumber, LocalDate dateOfBirth, Genderoptions genderoptions, String address, String pharmacyName, String pharmacyAddress) {
        super(username, password, roles, permissions, firstName, lastName, email, mobileNumber, dateOfBirth, genderoptions, address);
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
    }
}
