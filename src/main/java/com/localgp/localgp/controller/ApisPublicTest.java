package com.localgp.localgp.controller;

import com.localgp.localgp.entity.*;
import com.localgp.localgp.model.*;
import com.localgp.localgp.repository.UserAuthRepository;
import com.localgp.localgp.service.*;
import com.localgp.localgp.service.serviceImplementation.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("apisPublicTest/")
@CrossOrigin
public class ApisPublicTest {
    private UserAuthRepository userAuthRepository;

    @Autowired
    public DoctorService doctorService;

    @Autowired
    public PatientService patientService;

    @Autowired
    public PharmacyService pharmacyService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorScheduleService doctorScheduleService;
    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PrescriptionService prescriptionService;

    public ApisPublicTest(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }






    // Admin API Test
    @PostMapping(value = "newdoctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody DoctorDetailsModel doctorDetailsModel) {
        JSONObject jsonObject = new JSONObject();
        try {
            doctorService.createNewDoctor(doctorDetailsModel);
            jsonObject.put("message",  doctorDetailsModel.getUsername() +" saved successfully");
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

    @GetMapping("/users")
    public List<UserAuth> users(){
        return this.userAuthRepository.findAll();
    }
    @GetMapping(path="/allDoctors")
    public List<DoctorDetailsModel> doctors(){
        return doctorService.getAllDoctors();
    }





    //Appointment API TEST

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

    @GetMapping(path = "/getDoctorSchedule")
    public DoctorSchedule getDoctorSchedule(@RequestParam long id){
        return doctorScheduleService.getDoctorScheduleByDoctorId(id);
    }




    // DOCTOR API TEST

    @GetMapping(path = "/getDoctor")
    public Doctor getByDocId(@RequestParam long id){
        return doctorService.getById(id);
    }


    @GetMapping(path = "/testDoc/{username}")
    public DoctorDetailsModel getDoctorByUsername(@PathVariable String username) {
        DoctorDetailsModel doctorDetailsModel = null;
        try {
            doctorDetailsModel = new DoctorDetailsModel(doctorService.getByUsername(username));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return doctorDetailsModel;
    }

    @PostMapping(value = "/testDoc/{username}/update", produces = MediaType.APPLICATION_JSON_VALUE)
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

//    @GetMapping(path = "/getDoctorSchedule")
//    public DoctorSchedule getDoctorSchedule(@RequestParam long id){
//        return doctorScheduleService.getDoctorScheduleByDoctorId(id);
//    }

    @GetMapping(path = "/{username}/getDoctorSchedule")
    public DoctorSchedule getDoctorSchedule(@PathVariable String username){
        DoctorDetailsModel doctorDetailsModel =new DoctorDetailsModel(doctorService.getByUsername(username));
        return doctorScheduleService.getDoctorScheduleByDoctorId(doctorDetailsModel.getId());
    }
    // Schedule
    @PostMapping(path = "/updateDoctorSchedule" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDoctorSchedule(@RequestBody DoctorSchedule doctorSchedule){
        doctorScheduleService.updateDoctorSchedule(doctorSchedule);
    }




    // NOTIFICATION API TEST
    @GetMapping("/all")
    public List<Notification> showUserNotificationList(@RequestParam long userId) {
        return notificationService.getAll(userId);
    }

    @GetMapping("/allUnread")
    public List<Notification> showUserNotificationsUnread(@RequestParam long userId) {
        return notificationService.getUnReadNotifications(userId);
    }

    @GetMapping("/{notificationId}")
    public Notification showNotification(@PathVariable("notificationId") long notificationId, @PathVariable("userId") long userId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        notificationService.markRead(notificationId, userId);
        return notification;
    }

    @GetMapping("/markAllAsRead")
    public String processMarkAllAsRead(@RequestParam long userId) {
        notificationService.markAllRead(userId);
        return "Read Successfully";
    }

    //@Scheduled(cron = "*/4 * * * * *")
    @GetMapping("/user/notifications")
    public int getUnreadNotificationsCount(@RequestParam long userId) {
        return notificationService.getUnReadNotifications(userId).size();
    }




    // PATIENT API TEST
    @GetMapping(path = "/getPatient")
    public Patient getByPatientId(@RequestParam long id){
        return patientService.getByPatientId(id);
    }

    @GetMapping(path = "/testPatient/{username}")
    public PatientRegistrationModel getPatientByUsername(@PathVariable String username) {
        PatientRegistrationModel patientRegistrationModel=new PatientRegistrationModel(patientService.getPatientByUsername(username));
        return patientRegistrationModel;
    }

    @PostMapping(value = "/testPatient/{username}/update", produces = MediaType.APPLICATION_JSON_VALUE)
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





    // PHARMACY API TEST

    @GetMapping(path = "/getPharmacy")
    public Pharmacy getPharmacy(@RequestParam String pharmacyName){
        return pharmacyService.getByPharmacyName(pharmacyName);
    }

    @GetMapping(path = "/savePrescription")
    public String savePrescription(@RequestParam long id){
        return pharmacyService.savePrescription(id);
    }

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





    //

    // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }




    @PostMapping(value = "/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePassword(@RequestBody UpdatePasswordModel updatePasswordModel) {
        userPrincipalDetailsService.updatePassword(updatePasswordModel);
        return "Password Update Successful";
    }
}

