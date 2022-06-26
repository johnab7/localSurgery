package com.localgp.localgp.controller;

import com.localgp.localgp.entity.Doctor;
import com.localgp.localgp.entity.Patient;
import com.localgp.localgp.entity.UserAuth;
import com.localgp.localgp.model.dtoModel.DoctorDetailsModel;
import com.localgp.localgp.model.dtoModel.PatientRegistrationModel;
import com.localgp.localgp.model.dtoModel.UpdatePasswordModel;
import com.localgp.localgp.repository.UserAuthRepository;
import com.localgp.localgp.service.DoctorService;
import com.localgp.localgp.service.PatientService;
import com.localgp.localgp.service.serviceImplementation.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class ApisPublic {
    private UserAuthRepository userAuthRepository;

    @Autowired
    public DoctorService doctorService;
    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    public ApisPublic(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }

    @GetMapping(path="/allDoctors")
    public List<DoctorDetailsModel> doctors(){
        return doctorService.getAllDoctors();
    }

//    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> register(@RequestBody PatientRegistrationModel patientRegistrationModel) {
//        //log.info("UserResourceImpl : register");
//        JSONObject jsonObject = new JSONObject();
//        try {
//            patientService.registerPatient(patientRegistrationModel);
//            jsonObject.put("message",  " saved successfully");
////            patientRegistrationModel.getUsername() +
//            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
//        } catch (JSONException e) {
//            try {
//                jsonObject.put("exception", e.getMessage());
//            } catch (JSONException e1) {
//                e1.printStackTrace();
//            }
//            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
//        }
//    }

//    @PostMapping(path = "/register" , consumes = { "application/json" })
//    public void registerPatient(@RequestBody PatientRegistrationModel patientRegistrationModel ){
//        patientService.registerPatient(patientRegistrationModel);
//    }
//    @PostMapping("/register")
//    public ResponseEntity<Void> registerPatient(@RequestBody PatientRegistrationModel patientRegistrationModel, BindingResult bindingResult, Model model){
//        patientService.registerPatient(patientRegistrationModel);
//    }
//    @PostMapping("/register")
//    public void registerPatient(@ModelAttribute("patient") PatientRegistrationModel patientRegistrationModel, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
////            populateModel(model, patientRegistrationModel, "patient", "/customers/new/retail", null);
//            return ();
//        }
//        patientService.registerPatient(patientRegistrationModel);
//        model.addAttribute("createdUserName", patientRegistrationModel.getUsername());
//        return ();
//    }

//    public Model populateModel(Model model, PatientRegistrationModel patientRegistrationModel, String account_type, String action, String error) {
//        model.addAttribute("user", patientRegistrationModel);
//        model.addAttribute("account_type", account_type);
//        model.addAttribute("registerAction", action);
//        model.addAttribute("registrationError", error);
//        return model;
//    }
    // Available to doctors
    @GetMapping("doctor/reports")
    public String reports(){
        return "Some report data";
    }

    // Available to ADMIN
    @GetMapping("admin/users")
    public List<UserAuth> users(){
        return this.userAuthRepository.findAll();
    }

    @PostMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePassword(@RequestBody UpdatePasswordModel updatePasswordModel) {
        userPrincipalDetailsService.updatePassword(updatePasswordModel);
        return "Password Update Successful";
    }
}

