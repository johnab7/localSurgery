package com.localgp.localgp.controller;

import com.localgp.localgp.entity.Pharmacy;
import com.localgp.localgp.entity.Prescription;
import com.localgp.localgp.model.PrescriptionModel;
import com.localgp.localgp.service.PharmacyService;
import com.localgp.localgp.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
@CrossOrigin
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PrescriptionService prescriptionService;


    @GetMapping(path = "/getPharmacy")
    public Pharmacy getPharmacy(@RequestParam String pharmacyName){
        return pharmacyService.getByPharmacyName(pharmacyName);
    }

    @GetMapping(path = "/savePrescription")
    public String savePrescription(@RequestParam long id){
        return pharmacyService.savePrescription(id);
    }

    // Prescriptions

    @GetMapping(path = "/getPrescriptions")
    public List<Prescription> getAllPrescriptions(){
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping(path = "/getPrescriptionsByDoctor")
    public List<Prescription> getPrescriptionsByDoctor(@RequestParam long doctorId){
        return prescriptionService.getPrescriptionsByDoctor(doctorId);
    }

    @GetMapping(path = "/getPrescriptionsByPatient")
    public List<Prescription> getPrescriptionsByPatient(@RequestParam long patientId){
        return prescriptionService.getPrescriptionsByPatient(patientId);
    }

    @PostMapping(path = "/createPrescription" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPrescription(@RequestBody PrescriptionModel prescriptionModel){
        prescriptionService.createPrescription(prescriptionModel);
    }

}
