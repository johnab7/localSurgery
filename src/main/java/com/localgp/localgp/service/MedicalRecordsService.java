package com.localgp.localgp.service;

import com.localgp.localgp.entity.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordsService {

    void generateMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> getMedicalRecordsByPatientId(long patientId);

    MedicalRecord getByDiagnose( String diagnoseName);
    MedicalRecord getByDiagnoseStartDate(LocalDate startDate);
}
