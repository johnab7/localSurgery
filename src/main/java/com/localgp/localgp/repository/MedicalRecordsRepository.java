package com.localgp.localgp.repository;

import com.localgp.localgp.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findByDiagnosis(String diagnosis);
    List<MedicalRecord> findByPatientId(long patientId);
}
