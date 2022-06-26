package com.localgp.localgp.repository;

import com.localgp.localgp.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Prescription findById(long id);
    List<Prescription> findAllByPatientId(long id);

    List<Prescription> findAllByDoctorId(long doctorId);
//    List<Prescription> findAll();
}
