package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.Patient;
import com.localgp.localgp.model.PatientRegistrationModel;
import com.localgp.localgp.repository.PatientRepository;
import com.localgp.localgp.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PatientServiceImplementation implements PatientService {

    private final PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public PatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getByPatientId(long patientId) {
        return patientRepository.findById(patientId);
    }

    @Override
    public Patient getPatientByUsername(String username) {
        return patientRepository.findByUsername(username);
                //.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient registerPatientByEntity(Patient patient) {
        return patientRepository.saveAndFlush(patient);
    }
    @Override
    public void registerPatient(PatientRegistrationModel patientRegistrationModel) {
        String patientRole= "PATIENT";
        String passwordCreated= patientRegistrationModel.getPassword();
        Patient patient = new Patient(patientRegistrationModel, passwordEncoder.encode(passwordCreated), patientRole);
        patientRepository.saveAndFlush(patient);
    }

    @Override
    public void updatePatientDetails(PatientRegistrationModel updatePatientDetails) {
        Patient patient = patientRepository.getById(updatePatientDetails.getId());
        patient.updatePatient(updatePatientDetails);
        patientRepository.save(patient);
    }
}
