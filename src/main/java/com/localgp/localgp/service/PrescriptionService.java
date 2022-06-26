package com.localgp.localgp.service;

import com.localgp.localgp.entity.Prescription;
import com.localgp.localgp.model.dtoModel.PrescriptionModel;

import java.util.List;

public interface PrescriptionService {

    void createPrescription(PrescriptionModel prescriptionModel);

    List<Prescription> getPrescriptionsByPatient(long patientId);

    List<Prescription> getPrescriptionsByDoctor(long doctorId);

    List<Prescription> getAllPrescriptions();

//    List<Prescription> getPrescriptionsByDiagnose(String diagnoseName);
//
//    List<Prescription> getPrescriptionsByStatus(Boolean prescriptionStatus);
}
