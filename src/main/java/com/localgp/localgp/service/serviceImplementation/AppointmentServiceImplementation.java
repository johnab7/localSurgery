package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.Appointment;
import com.localgp.localgp.entity.AppointmentStatus;
import com.localgp.localgp.entity.Doctor;
import com.localgp.localgp.entity.DoctorSchedule;
import com.localgp.localgp.model.dtoModel.AppointmentRegistrationModel;
import com.localgp.localgp.model.viewModel.DaySchedule;
import com.localgp.localgp.model.viewModel.Period;

import com.localgp.localgp.repository.AppointmentRepository;
import com.localgp.localgp.repository.DoctorScheduleRepository;
import com.localgp.localgp.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final PharmacyService pharmacyService;
    private final DoctorScheduleRepository scheduleRepository;
    private final NotificationService notificationService;


    @Override
    public Appointment getAppointmentById(long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteAppointmentById(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }


    @Override
    public void createNewAppointment(AppointmentRegistrationModel appointmentRegistrationModel){
        if(isAvailable(appointmentRegistrationModel.getDoctorId(), appointmentRegistrationModel.getPatientId(),
                appointmentRegistrationModel.getStartTime())){
            Appointment appointment = new Appointment();
            appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
            appointment.setPatient(patientService.getByPatientId(appointmentRegistrationModel.getPatientId()));
            appointment.setDoctor(doctorService.getById(appointmentRegistrationModel.getDoctorId()));
            appointment.setStartTime(appointmentRegistrationModel.getStartTime());
            appointment.setEndTime(appointmentRegistrationModel.getStartTime().plusMinutes(30));
            appointmentRepository.save(appointment);
            notificationService.newAppointmentNotification(appointment);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void updateAllAppointmentStatus() {
        appointmentRepository.findScheduledWithEndBeforeDate(LocalDateTime.now())
                .forEach(appointment -> {
                    appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
                    updateAppointment(appointment);
                });
    }

    @Override
    public List<Appointment> getAllPatientAppointments(long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public void updateAppointmentStatusByPatient(long patientId) {

    }

    @Override
    public List<Appointment> getAllDoctorAppointments(long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAllDoctorAppointmentsByDate(long doctorId, LocalDate day) {
        return appointmentRepository.findByDoctorIdWithStartInPeriod(doctorId, day.atStartOfDay(), day.atStartOfDay().plusDays(1));
    }

    @Override
    public List<Appointment> getAllPatientAppointmentsByDate(long patientId, LocalDate day) {
        return appointmentRepository.findByPatientIdWithStartInPeriod(patientId, day.atStartOfDay(), day.atStartOfDay().plusDays(1));
    }

    @Override
    public List<Appointment> getConfirmedAppointmentsByPatientId(long patientId) {
        return null;
    }

    @Override
    public List<Period> getAvailableHours(long doctorId, long patientId, LocalDate date) {
        Doctor doctor = doctorService.getById(doctorId);
        DoctorSchedule doctorSchedule = doctor.getDoctorSchedule();
        DaySchedule selectedDay = doctorSchedule.getDay(date.getDayOfWeek().toString().toLowerCase());

        List<Appointment> doctorAppointments = getAllDoctorAppointmentsByDate(doctorId, date);
        List<Appointment> patientAppointments = getAllPatientAppointmentsByDate(patientId, date);
        List<Period> availablePeriods = selectedDay.workingList();
        //availablePeriods = selectedDay.getWorkingHoursList();
//        availablePeriods= selectedDay.workingList();

        if(!doctorAppointments.isEmpty()){
            availablePeriods.addAll(excludeAppointmentsFromTimePeriods(availablePeriods, doctorAppointments));
        }
        if(!patientAppointments.isEmpty()){
            availablePeriods.addAll(excludeAppointmentsFromTimePeriods(availablePeriods, patientAppointments));
        }

        return calculateWorkSlots(availablePeriods);

    }

    @Override
    public List<Period> calculateWorkSlots(List<Period> availableTimePeriods) {
        ArrayList<Period> availableHours = new ArrayList();
        for (Period period : availableTimePeriods) {
            Period workSlots = new Period(period.getStartTime(), period.getStartTime().plusMinutes(30));
            while (workSlots.getEndTime().isBefore(period.getEndTime()) || workSlots.getEndTime().equals(period.getEndTime())) {
                availableHours.add(new Period(workSlots.getStartTime(), workSlots.getStartTime().plusMinutes(30)));
                workSlots.setStartTime(workSlots.getStartTime().plusMinutes(30));
                workSlots.setEndTime(workSlots.getEndTime().plusMinutes(30));
            }
        }
        return availableHours;
    }

    @Override
    public List<Period> excludeAppointmentsFromTimePeriods(List<Period> periods, List<Appointment> appointments) {
        List<Period> toAdd = new ArrayList();
        Collections.sort(appointments);
        for (Appointment appointment : appointments) {
            for (Period period : periods) {
                if ((appointment.getStartTime().toLocalTime().isBefore(period.getStartTime()) ||
                        appointment.getStartTime().toLocalTime().equals(period.getStartTime())) &&
                        appointment.getEndTime().toLocalTime().isAfter(period.getStartTime()) &&
                        appointment.getEndTime().toLocalTime().isBefore(period.getEndTime())) {
                    period.setStartTime(appointment.getEndTime().toLocalTime());
                }
                if (appointment.getStartTime().toLocalTime().isAfter(period.getStartTime()) &&
                        appointment.getStartTime().toLocalTime().isBefore(period.getEndTime()) &&
                        appointment.getEndTime().toLocalTime().isAfter(period.getEndTime()) ||
                        appointment.getEndTime().toLocalTime().equals(period.getEndTime())) {
                    period.setEndTime(appointment.getStartTime().toLocalTime());
                }
                if (appointment.getStartTime().toLocalTime().isAfter(period.getStartTime()) &&
                        appointment.getEndTime().toLocalTime().isBefore(period.getEndTime())) {
                    toAdd.add(new Period(period.getStartTime(), appointment.getStartTime().toLocalTime()));
                    period.setStartTime(appointment.getEndTime().toLocalTime());
                }
            }
        }
        periods.addAll(toAdd);
        Collections.sort(periods);
        return periods;
    }

    @Override
    public void cancelPatientAppointmentById(long appointmentId, long patientId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        if (appointment.getPatient().getId() == patientId) {
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
            appointmentRepository.save(appointment);
            notificationService.appointmentCanceledByPatientNotification(appointment);
        } else {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized");
        }

    }

    @Override
    public void cancelDoctorAppointmentById(long appointmentId, long doctorId) {
        Appointment appointment = appointmentRepository.getById(appointmentId);
        if (appointment.getDoctor().getId() == doctorId) {
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
            appointmentRepository.save(appointment);
            notificationService.appointmentCanceledByDoctorNotification(appointment);
        } else {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized");
        }
    }

    @Override
    public long getNumberOfScheduledAppointmentsForPatient(long patientId) {
        return appointmentRepository.findConfirmedByPatientId(patientId).size();
    }

    @Override
    public long getNumberOfScheduledAppointmentsForDoctor(long doctorId) {
        return appointmentRepository.findConfirmedByDoctorId(doctorId).size();
    }

    @Override
    public boolean isAvailable(long doctorId, long patientId, LocalDateTime aptTime) {
        Period period = new Period(aptTime.toLocalTime(), aptTime.toLocalTime().plusMinutes(30));
        return getAvailableHours(doctorId, patientId, aptTime.toLocalDate()).contains(period);
    }
}
