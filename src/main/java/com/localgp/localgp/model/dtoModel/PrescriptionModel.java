package com.localgp.localgp.model.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionModel {

    private String diagnosis;
    private String diagnosisDescription;
    private String medicineDescription;
//    private String dosage;
//    private String dosageDescription;
    private Boolean status;
    private LocalDate prescriptionDate;
    private int prescriptionPeriod;

    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private Long pharmacyId;


}
