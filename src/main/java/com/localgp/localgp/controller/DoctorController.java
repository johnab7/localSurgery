package com.localgp.localgp.controller;

import com.localgp.localgp.entity.Doctor;
import com.localgp.localgp.entity.DoctorSchedule;
import com.localgp.localgp.model.dtoModel.DoctorDetailsModel;
import com.localgp.localgp.service.DoctorScheduleService;
import com.localgp.localgp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorController {

    @Autowired
    public DoctorService doctorService;

    @Autowired
    public DoctorScheduleService scheduleService;

    @GetMapping(path = "/getDoctor")
    public Doctor getById(@RequestParam long id){
        return doctorService.getById(id);
    }


    @GetMapping(path = "/{username}")
    public DoctorDetailsModel getDoctorByUsername(@PathVariable String username) {
        DoctorDetailsModel doctorDetailsModel = null;
        try {
           doctorDetailsModel = new DoctorDetailsModel(doctorService.getByUsername(username));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return doctorDetailsModel;
    }

    @PostMapping(value = "/{username}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateDoctorData(@PathVariable String username, @RequestBody DoctorDetailsModel doctorDetailsModel) {
        JSONObject jsonObject = new JSONObject();
        try {
            doctorService.updateDoctorDetails(doctorDetailsModel);
            jsonObject.put("message",  doctorDetailsModel.getUsername() +" saved successfully");
//
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new org.springframework.http.ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path = "/getDoctorSchedule")
    public DoctorSchedule getDoctorSchedule(@RequestParam long id){
        return scheduleService.getDoctorScheduleByDoctorId(id);
    }

    @GetMapping(path = "/{username}/getDoctorSchedule")
    public DoctorSchedule getDoctorSchedule(@PathVariable String username){
        DoctorDetailsModel doctorDetailsModel =new DoctorDetailsModel(doctorService.getByUsername(username));
        return scheduleService.getDoctorScheduleByDoctorId(doctorDetailsModel.getId());
    }
    // Schedule
    @PostMapping(path = "/updateDoctorSchedule" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDoctorSchedule(@RequestBody DoctorSchedule doctorSchedule){
        scheduleService.updateDoctorSchedule(doctorSchedule);
    }

}
