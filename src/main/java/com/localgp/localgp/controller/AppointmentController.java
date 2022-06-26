package com.localgp.localgp.controller;


import com.localgp.localgp.entity.Appointment;
import com.localgp.localgp.entity.DoctorSchedule;
import com.localgp.localgp.model.AppointmentRegistrationModel;
import com.localgp.localgp.model.Period;
import com.localgp.localgp.service.AppointmentService;
import com.localgp.localgp.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorScheduleService doctorScheduleService;

    @GetMapping(path = "/getAppointment")
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping(path = "/availability")
    public List<Period> getAvailableHours(@RequestParam(value = "dId") long doctorId,
                                          @RequestParam(value = "uId") long patientId, @RequestParam(value = "date")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Period> availableSlots= appointmentService.getAvailableHours(doctorId, patientId, date);
        return availableSlots;
    }

    @PostMapping(path = "/createNewAppointment" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewAppointment(AppointmentRegistrationModel appointmentRegistrationModel){
        appointmentService.createNewAppointment(appointmentRegistrationModel);
    }

    @GetMapping(path = "/cancel/patient")
    public void cancelPatientAppointment(@RequestParam long appointmentId , @RequestParam long patientId){
        appointmentService.cancelPatientAppointmentById(appointmentId , patientId);
    }

    @GetMapping(path = "/cancel/doctor")
    public void cancelDoctorAppointment(@RequestParam long appointmentId , @RequestParam long doctorId){
        appointmentService.cancelDoctorAppointmentById(appointmentId , doctorId);
    }

    @GetMapping(path = "/getAllDoctorAppointments")
    public List<Appointment> getAllDoctorAppointments(@RequestParam long id){
        return appointmentService.getAllDoctorAppointments(id);
    }

    // Schedule

    @GetMapping(path = "/getAllDoctorSchedule")
    public DoctorSchedule getDoctorSchedule(@RequestParam long id){
        return doctorScheduleService.getDoctorScheduleByDoctorId(id);
    }

}
