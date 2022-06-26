package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.MedicalRecord;
import com.localgp.localgp.model.dtoModel.MedicalRecordModel;
import com.localgp.localgp.model.dtoModel.PrescriptionModel;
import com.localgp.localgp.repository.MedicalRecordsRepository;
import com.localgp.localgp.service.MedicalRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class MedicalRecordsServiceImplementation implements MedicalRecordsService {

    private final MedicalRecordsRepository medicalRecordsRepository;


    @Override
    public void generateMedicalRecord(MedicalRecord medicalRecord) {

        //MedicalRecord
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatientId(long patientId) {
        return null;
    }

    @Override
    public MedicalRecord getByDiagnose(String diagnoseName) {
        return null;
    }

    @Override
    public MedicalRecord getByDiagnoseStartDate(LocalDate startDate) {
        return null;
    }
}
