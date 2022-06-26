package com.localgp.localgp.repository;

import com.localgp.localgp.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface PatientRepository extends GroupedUserRepository<Patient>{
//        extends JpaRepository<Patient, Long> {
//    Patient findOneByUserId(long userId);
//
//    List<Patient> findAllByDoctorId(long doctorId);
//
//    Page<Patient> findAllByDoctorIdOrderByFirstNameAsc(long doctorId, Pageable pageable);
//
//    Patient findById(long id);
}
