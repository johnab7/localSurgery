package com.localgp.localgp.model;

import com.localgp.localgp.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailsModel extends UserRegistrationModel {

    private final String hospitalName="UOL";

    private String department;

    private String roomNumber;

    public DoctorDetailsModel(Doctor doctor) {
        super(doctor);
        this.setDepartment(doctor.getDepartment());
        this.setRoomNumber(doctor.getRoomNumber());
    }

}
