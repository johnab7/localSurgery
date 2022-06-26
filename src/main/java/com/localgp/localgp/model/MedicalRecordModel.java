package com.localgp.localgp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordModel {

    private String diagnose;
    private String diagnoseDescription;
    private LocalDate diagStart;
    private LocalDate diagEnd;
    private Long patientId;
    private Long doctorId;

}
