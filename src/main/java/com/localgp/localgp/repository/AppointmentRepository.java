package com.localgp.localgp.repository;

import com.localgp.localgp.entity.Appointment;
import com.localgp.localgp.entity.AppointmentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

//    @Query("select a from Appointment a where a.patient.id = :patientId")
//    List<Appointment> findByPatientId(@Param("patientId") int patientId);

    List<Appointment> findByPatientId(long patientId);

//    @Query("select a from Appointment a where a.doctor.id = :doctorId")
//    List<Appointment> findByDoctorId(@Param("doctorId") int doctorId);

    List<Appointment>findByDoctorId(long doctorId);

    List<Appointment>findByAppointmentStatusAndPatientId(String appointmentStatus, Long patientId);

    @Query("select a from Appointment a where a.doctor.id = :doctorId and  a.startTime >=:dayStart and  a.startTime <=:dayEnd")
    List<Appointment> findByDoctorIdWithStartInPeriod(@Param("doctorId") long doctorId, @Param("dayStart") LocalDateTime startPeriod, @Param("dayEnd") LocalDateTime endPeriod);
//    List<Appointment>findByDoctorIdAndAndStartTime(Long doctorId, LocalDateTime startTime
//    List<Appointment>findByDoctorIdAndAndStartTime(Long doctorId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("select a from Appointment a where a.patient.id = :patientId and  a.startTime >=:dayStart and  a.startTime <=:dayEnd")
    List<Appointment> findByPatientIdWithStartInPeriod(@Param("patientId") long patientId, @Param("dayStart") LocalDateTime startPeriod, @Param("dayEnd") LocalDateTime endPeriod);

//    List<Appointment>findByPatientIdAndAndStartTime(Long patientId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("select a from Appointment a where a.appointmentStatus = 'SCHEDULED' and :now >= a.endTime")
    List<Appointment> findScheduledWithEndBeforeDate(@Param("now") LocalDateTime now);

//    List<Appointment>findConfirmedWithEndBeforeDate(LocalDateTime now);
//    List<Appointment>findByAppointmentStatusBeforeAndEndTime(LocalDateTime now);

    @Query("select a from Appointment a where a.appointmentStatus = 'SCHEDULED' and :date >= a.endTime and (a.patient.id = :userId)")
    List<Appointment> findScheduledByUserIdWithEndBeforeDate(@Param("date") LocalDateTime date, @Param("userId") long userId);

//    List<Appointment> findConfirmedByUserIdWithEndBeforeDate(LocalDateTime date, Long userId);
//    List<Appointment>findByAppointmentStatusBeforeAndPatientIdOrDoctorId(AppointmentStatus appointmentStatus, LocalDateTime dateTime, Long userAuthId);

//    @Query("select a from Appointment a where a.appointmentStatus = 'FINISHED' and :date >= a.endTime")
//    List<Appointment> findFinishedWithEndBeforeDate(@Param("date") LocalDateTime date);

//    List<Appointment>findCompletedAppointments(LocalDateTime dateTime);

//    @Query("select a from Appointment a where a.appointmentStatus = 'FINISHED' and :date >= a.endTime and (a.patient.id = :userId or a.doctor.id = :userId)")
//    List<Appointment> findFinishedByUserIdWithEndBeforeDate(@Param("date") LocalDateTime date, @Param("userId") int userId);

//    List<Appointment>findCompletedByUserId(Long userAuthId,AppointmentStatus appointmentStatus, LocalDateTime dateTime);

    @Query("select a from Appointment a where a.appointmentStatus = 'SCHEDULED' and a.patient.id = :patientId")
    List<Appointment> findConfirmedByPatientId(@Param("patientId") long patientId);

    @Query("select a from Appointment a where a.appointmentStatus = 'SCHEDULED' and a.doctor.id = :doctorId")
    List<Appointment> findConfirmedByDoctorId(@Param("doctorId") long doctorId);

//    List<Appointment>findConfirmedByPatientId(Long patientID, AppointmentStatus appointmentStatus);
//
//    List<Appointment>findRequestedByDoctorId(Long doctorId, AppointmentStatus appointmentStatus);
}
