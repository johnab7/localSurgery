package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.MedicalRecord;
import com.localgp.localgp.entity.Prescription;
import com.localgp.localgp.model.PrescriptionModel;
import com.localgp.localgp.repository.MedicalRecordsRepository;
import com.localgp.localgp.repository.PrescriptionRepository;
import com.localgp.localgp.service.PrescriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PrescriptionServiceImplementation implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    private final MedicalRecordsRepository medicalRecordsRepository;


    @Override
    public void createPrescription(PrescriptionModel prescriptionModel) {
        Prescription prescription = new Prescription(prescriptionModel);
        prescription.setStatus(false);
        prescription.setPrescriptionDate(LocalDate.now());
        prescriptionRepository.save(prescription);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDiagnosis(prescription.getDiagnosis());
        medicalRecord.setDiagnosisDescription(prescription.getDiagnosisDescription());
        medicalRecord.setStartDate(prescription.getPrescriptionDate());
        medicalRecord.setEndDate(LocalDate.parse("9999-12-12"));
        medicalRecord.setPatient(prescription.getPatient());
        medicalRecord.setDoctor(prescription.getDoctor());
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription);
        medicalRecord.setPrescriptions(prescriptions);
        medicalRecordsRepository.save(medicalRecord);



    }

    @Override
    public List<Prescription> getPrescriptionsByPatient(long patientId) {
        return prescriptionRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(long doctorId) {
        return prescriptionRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }
}
