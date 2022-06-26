package com.localgp.localgp.controller;


import com.localgp.localgp.model.PatientRegistrationModel;
import com.localgp.localgp.repository.UserAuthRepository;
import com.localgp.localgp.service.PatientService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class PublicRegistrationController {
    private UserAuthRepository userAuthRepository;

    public final PatientService patientService;

    public PublicRegistrationController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody PatientRegistrationModel patientRegistrationModel) {
        JSONObject jsonObject = new JSONObject();
        try {
            patientService.registerPatient(patientRegistrationModel);
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
