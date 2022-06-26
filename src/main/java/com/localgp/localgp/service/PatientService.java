package com.localgp.localgp.service;


import com.localgp.localgp.entity.Patient;
import com.localgp.localgp.model.PatientRegistrationModel;

import java.util.List;

public interface PatientService {
//    void create(PatientRegistrationModel registrationModel);
//
//    void save(EditPatientModel editPatientModel);
//
//    PatientViewModel getById(long id);
//
    Patient getByPatientId(long patientId);
    Patient getPatientByUsername(String username);

    List<Patient> getAllPatients();

    void registerPatient(PatientRegistrationModel patientRegistrationModel);
    Patient registerPatientByEntity(Patient patient);

    void updatePatientDetails(PatientRegistrationModel updatePatientDetails);
//
//    EditPatientModel getEditModelByUserId(long userId);
//
//    PatientBasicViewModel getBasicById(long id);
//
//    List<PatientBasicViewModel> getPatientsByDoctorId(long doctorId);
//
//    Page<PatientViewModel> getPatientsByDoctorId(Pageable pageable, long doctorId);
//
//    Page<PatientViewModel> getAll(Pageable pageable);
}
