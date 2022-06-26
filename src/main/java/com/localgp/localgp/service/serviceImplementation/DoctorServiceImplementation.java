package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.Doctor;
import com.localgp.localgp.entity.DoctorSchedule;
import com.localgp.localgp.model.DoctorDetailsModel;
import com.localgp.localgp.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.localgp.localgp.service.DoctorService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorServiceImplementation(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Doctor getById(long id) {
        Doctor doctor;
        doctor = doctorRepository.findById(id);

        return doctor;
    }


    @Override
    public List<DoctorDetailsModel> getAllDoctors() {
        List<DoctorDetailsModel> doctors= new ArrayList<>();
        List<Doctor> docs = doctorRepository.findAll();
        for (Doctor doc: docs) {
            DoctorDetailsModel temp = new DoctorDetailsModel(doc);
            doctors.add(temp);
        }
        return doctors;
    }

    @Override
    public Doctor getByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    @Override
    public void createNewDoctor(DoctorDetailsModel doctorDetailsModel) {
        DoctorSchedule doctorSchedule = DoctorSchedule.generateDefaultDoctorSchedule();
        String doctorRole= "DOCTOR";
        Doctor doctor = new Doctor(doctorDetailsModel, passwordEncoder.encode(doctorDetailsModel.getPassword()), doctorRole, doctorSchedule);
        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctorDetails(DoctorDetailsModel updateDoctorDetailsModel) {
        Doctor doctor = doctorRepository.getById(updateDoctorDetailsModel.getId());
        doctor.updateUser(updateDoctorDetailsModel);
        doctorRepository.save(doctor);
    }
}
