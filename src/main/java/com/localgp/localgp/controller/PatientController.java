package com.localgp.localgp.controller;

import com.localgp.localgp.entity.Patient;
import com.localgp.localgp.model.PatientRegistrationModel;
import com.localgp.localgp.service.PatientService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {

    public final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "/getPatient")
    public Patient getById(@RequestParam long id){
        return patientService.getByPatientId(id);
    }

    @GetMapping(path = "/{username}")
    public PatientRegistrationModel getPatientByUsername(@PathVariable String username) {
        PatientRegistrationModel patientRegistrationModel=new PatientRegistrationModel(patientService.getPatientByUsername(username));
        return patientRegistrationModel;
    }

    @PostMapping(value = "/{username}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePatientData(@PathVariable String username, @RequestBody PatientRegistrationModel patientRegistrationModel) {
        JSONObject jsonObject = new JSONObject();
        try {
            patientService.updatePatientDetails(patientRegistrationModel);
            jsonObject.put("message",  patientRegistrationModel.getUsername() +" saved successfully");
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
