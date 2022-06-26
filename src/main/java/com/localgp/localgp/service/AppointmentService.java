package com.localgp.localgp.service;

import com.localgp.localgp.entity.Appointment;
import com.localgp.localgp.model.AppointmentRegistrationModel;
import com.localgp.localgp.model.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

public interface AppointmentService {

    Appointment getAppointmentById(long id);
    void deleteAppointmentById(long appointmentId);

    void createNewAppointment(AppointmentRegistrationModel appointmentRegistrationModel);

    void updateAppointment(Appointment appointment);


    List<Appointment> getAllAppointments();

    void updateAllAppointmentStatus();

    List<Appointment> getAllPatientAppointments(long patientId);
    void updateAppointmentStatusByPatient(long patientId);

    List<Appointment> getAllDoctorAppointments(long doctorId);

    List<Appointment> getAllDoctorAppointmentsByDate(long doctorId, LocalDate day);

    List<Appointment> getAllPatientAppointmentsByDate(long patientId, LocalDate day);

    List<Appointment> getConfirmedAppointmentsByPatientId(long patientId);

    List<Period> getAvailableHours(long doctorId, long patientId, LocalDate date);

    List<Period> calculateWorkSlots(List<Period> availableTimePeriods);

    List<Period> excludeAppointmentsFromTimePeriods(List<Period> periods, List<Appointment> appointments);

    void cancelPatientAppointmentById(long appointmentId, long patientId);
    void cancelDoctorAppointmentById(long appointmentId, long doctorId);

    long getNumberOfScheduledAppointmentsForPatient(long patientId);
    long getNumberOfScheduledAppointmentsForDoctor(long doctorId);
    boolean isAvailable(long doctorId, long patientId, LocalDateTime start);
}
