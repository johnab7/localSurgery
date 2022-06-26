package com.localgp.localgp.repository;


import com.localgp.localgp.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.localgp.localgp.entity.Genderoptions.*;

@Service
public class DbInitialise implements CommandLineRunner {
    private UserAuthRepository userAuthRepository;
    private PasswordEncoder passwordEncoder;

    private DoctorRepository doctorRepository;

    private PatientRepository patientRepository;

    private PharmacyRepository pharmacyRepository;

    DoctorSchedule doctorSchedule = DoctorSchedule.generateDefaultDoctorSchedule();
    DoctorSchedule doctorSchedule2 = DoctorSchedule.generateDefaultDoctorSchedule();

    public DbInitialise(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder, DoctorRepository doctorRepository, PatientRepository patientRepository, PharmacyRepository pharmacyRepository) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.pharmacyRepository= pharmacyRepository;
    }

    @Override
    public void run(String... args) {
        // Delete all
//        this.userAuthRepository.deleteAll();



////       Users data init
//        UserAuth admin= new UserAuth("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1, ACCESS_TEST2", "admin",
//                "admin", "admin@uol.com", "9111147570", LocalDate.of (2000,12,20), MALE, "1220 admin");
//
//        UserAuth admin1= new UserAuth("admin1", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1, ACCESS_TEST2", "admin1",
//                "admin1", "admin1@uol.com", "9440047571", LocalDate.of (2000,12,21), MALE, "1221 admin1");
////
//        Doctor doc= new Doctor("doc", passwordEncoder.encode("doctor123"), "DOCTOR", "ACCESS_TEST1", "doc",
//                "doc", "doc@uol.com", "8440047572", LocalDate.of (2000,11,20), MALE, "1120 doc doc", "GP","GP100", doctorSchedule);
//
//        Doctor doc1= new Doctor("doc1", passwordEncoder.encode("doctor123"), "DOCTOR", "ACCESS_TEST1", "doc1",
//                "doc1", "doc1@uol.com", "8440047573", LocalDate.of (2000,11,21), MALE, "1121 doc1 doc1", "General Physician", "GP101", doctorSchedule2);
////
//        Patient john = new Patient("john", passwordEncoder.encode("patient123"), "PATIENT", "ACCESS_TEST1", "john",
//                "john", "john@uol.com", "7440047574", LocalDate.of (2000,10,20), MALE, "1020 john john", 50, 100, 1111111111);
//
//        Patient emma = new Patient("emma", passwordEncoder.encode("patient123"), "PATIENT", "ACCESS_TEST1", "emma",
//                "emma", "emma@uol.com", "7440047575", LocalDate.of (2000,10,21), FEMALE, "1021 emma emma", 60, 170, 1111111112);
//
//        Pharmacy pharmacy = new Pharmacy("pharma", passwordEncoder.encode("pharmacy123"), "PHARMACY", "ACCESS_TEST1", "pharma",
//                "pharma", "pharma@uol.com", "6440047576", LocalDate.of (2000,9,20), MALE, "920 pharma pharma", "pharma pharma", "920 pharma pharma");
//
//        Pharmacy davisPharmacy = new Pharmacy("davispharma", passwordEncoder.encode("pharmacy123"), "PHARMACY", "ACCESS_TEST1", "davis",
//                "pharma", "davispharma@uol.com", "6440047577", LocalDate.of (2000,9,21), MALE, "921 davis pharma", "Davis Pharma", "921 davis pharma");
//
//        //IGNORE
////        Doctor doc3= new Doctor("doc3", passwordEncoder.encode("doctor123"), "DOCTOR", "ACCESS_TEST1", "doc3",
////                "doc3", "doc3@uol.com", "8440047574", LocalDate.of (2000,11,20), MALE, "1122 doc3 doc3", "GP","GP102");
////
////        Doctor doc4= new Doctor("doc4", passwordEncoder.encode("doctor123"), "DOCTOR", "ACCESS_TEST1", "doc4",
////                "doc4", "doc4@uol.com", "8440047575", LocalDate.of (2000,11,21), MALE, "1123 doc4 doc4", "General Physician", "GP103");
//
//
//
////         Save to db
//        List<UserAuth> userAuths = Arrays.asList(admin,admin1);
//        this.userAuthRepository.saveAll(userAuths);
//
//        List<Doctor> doctors = Arrays.asList(doc,doc1);
//        this.doctorRepository.saveAll(doctors);
//
//        List<Patient> patients= Arrays.asList(john,emma);
//        this.patientRepository.saveAll(patients);
//
//        List<Pharmacy> pharmacies= Arrays.asList(pharmacy,davisPharmacy);
//        this.pharmacyRepository.saveAll(pharmacies);

////        List<Doctor> doctors = Arrays.asList(doc3,doc4);
////        this.doctorRepository.saveAll(doctors);


    }
}