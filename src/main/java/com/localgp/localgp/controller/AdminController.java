package com.localgp.localgp.controller;

import com.localgp.localgp.model.dtoModel.DoctorDetailsModel;
import com.localgp.localgp.model.dtoModel.PatientRegistrationModel;
import com.localgp.localgp.service.DoctorService;
import com.localgp.localgp.service.PatientService;
import com.localgp.localgp.service.PharmacyService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    public final DoctorService doctorService;
    public final PatientService patientService;

    public final PharmacyService pharmacyService;

    public AdminController(DoctorService doctorService, PatientService patientService, PharmacyService pharmacyService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.pharmacyService = pharmacyService;
    }

    @PostMapping(value = "/newdoctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody DoctorDetailsModel doctorDetailsModel) {
        JSONObject jsonObject = new JSONObject();
        try {
            doctorService.createNewDoctor(doctorDetailsModel);
            jsonObject.put("message",  doctorDetailsModel.getUsername() +" saved successfully");
//
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
}
